package br.cericatto.devgrid.presenter.di.component

import android.content.Context
import android.content.SharedPreferences
import br.cericatto.devgrid.presenter.di.module.ApplicationModule
import dagger.Component
import retrofit2.Retrofit

/**
 * ApplicationComponent.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun exposeRetrofit(): Retrofit
    fun exposeContext(): Context
}