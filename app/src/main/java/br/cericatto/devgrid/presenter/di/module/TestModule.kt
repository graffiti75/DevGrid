package br.cericatto.devgrid.presenter.di.module

import br.cericatto.devgrid.presenter.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * TestModule.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 27, 2019
 */
@Module
class TestModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}