package br.cericatto.devgrid.view.activity

import android.content.Intent
import android.os.Bundle
import br.cericatto.devgrid.R
import br.cericatto.devgrid.presenter.presenter.impl.MainPresenterImpl
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.di.component.DaggerMainComponent
import br.cericatto.devgrid.presenter.di.module.MainModule
import javax.inject.Inject

/**
 * MainActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
class MainActivity : BaseActivity() {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    @Inject
    lateinit var mPresenter: MainPresenterImpl

    @Inject
    lateinit var mApiService: ApiService

    //--------------------------------------------------
    // Base Activity
    //--------------------------------------------------

    override val contentView: Int
        get() = R.layout.activity_main

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        setContentView(contentView)
        super.onViewReady(savedInstanceState, intent)
    }

    override fun resolveDaggerDependency() {
        DaggerMainComponent.builder()
            .applicationComponent(applicationComponent)
            .mainModule(MainModule(this))
            .build().inject(this)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    //--------------------------------------------------
    // Activity Lifecycle
    //--------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomToolbar(false, getString(R.string.activity_main))
        mPresenter.initDataSet(mApiService)
    }
}
