package br.cericatto.devgrid.presenter.di.module

import android.content.Context
import br.cericatto.devgrid.BuildConfig
import br.cericatto.devgrid.MainApplication
import br.cericatto.devgrid.presenter.checkIfHasNetwork
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * ApplicationModule.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
@Module
class ApplicationModule(private val mContext: Context, private val mBaseUrl: String) {
    @Provides
    internal fun provideGsonConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        })

        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(provideOfflineCacheInterceptor())
            .addNetworkInterceptor(provideCacheInterceptor())
            .cache(provideCache())
            .build()
    }

    @Provides
    internal fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: MoshiConverterFactory,
        adapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }

    //--------------------------------------------------
    // Interceptor Methods
    //--------------------------------------------------

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(mContext.cacheDir, "http-cache"), (10 * 1024 * 1024).toLong()) // 10 MB
        } catch (e: Exception) {
            Timber.e(e, "Could not create Cache!")
        }
        return cache
    }

    private fun provideCacheInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())

                // re-write response header to force use of cache
                val cacheControl = CacheControl.Builder()
                    .maxAge(2, TimeUnit.MINUTES)
                    .build()

                return response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
            }
        }
    }

    private fun provideOfflineCacheInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()
                if (!mContext.checkIfHasNetwork()) {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                    request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
                }
                return chain.proceed(request)
            }
        }
    }
}