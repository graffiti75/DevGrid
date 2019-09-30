package br.cericatto.devgrid.presenter.di.module

import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.di.scope.PerActivity
import br.cericatto.devgrid.view.activity.DetailActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * DetailModule.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 29, 2019
 */
@Module
class DetailModule(private val mActivity: DetailActivity) {
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @PerActivity
    @Provides
    fun provideActivity(): DetailActivity {
        return mActivity
    }
}