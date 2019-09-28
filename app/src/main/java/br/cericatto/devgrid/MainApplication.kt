package br.cericatto.devgrid

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
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

    // FIXME
    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}