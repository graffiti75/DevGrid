package br.cericatto.devgrid.presenter.di.module

import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.di.scope.PerActivity
import br.cericatto.devgrid.view.MainActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * MainModule.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
@Module
class MainModule(private val mActivity: MainActivity) {
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @PerActivity
    @Provides
    fun provideActivity(): MainActivity {
        return mActivity
    }
}