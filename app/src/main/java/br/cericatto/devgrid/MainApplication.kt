package br.cericatto.devgrid

import android.app.Application
import br.cericatto.devgrid.presenter.LineNumberDebugTree
import br.cericatto.devgrid.presenter.ReleaseTree
import br.cericatto.devgrid.presenter.di.component.ApplicationComponent
import br.cericatto.devgrid.presenter.di.component.DaggerApplicationComponent
import br.cericatto.devgrid.presenter.di.module.ApplicationModule
import timber.log.Timber

/**
 * MainApplication.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
open class MainApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    lateinit var login: String
    lateinit var password: String

    var page: Int = 1
    var loadedAllData: Boolean = false

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initializeApplicationComponent()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(LineNumberDebugTree())
        else Timber.plant(ReleaseTree())
    }

    private fun initializeApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this, AppConfiguration.BASE_URL))
            .build()
    }
}