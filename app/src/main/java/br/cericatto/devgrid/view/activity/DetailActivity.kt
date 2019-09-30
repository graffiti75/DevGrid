package br.cericatto.devgrid.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.R
import br.cericatto.devgrid.presenter.presenter.impl.DetailPresenterImpl
import br.cericatto.devgrid.presenter.NavigationUtils
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.di.component.DaggerDetailComponent
import br.cericatto.devgrid.presenter.di.module.DetailModule
import javax.inject.Inject

/**
 * DetailActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 29, 2019
 */
class DetailActivity : BaseActivity() {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    @Inject
    lateinit var mPresenter: DetailPresenterImpl

    @Inject
    lateinit var mApiService: ApiService

    //--------------------------------------------------
    // Base Activity
    //--------------------------------------------------

    override val contentView: Int
        get() = R.layout.activity_detail

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        setContentView(contentView)
        super.onViewReady(savedInstanceState, intent)
    }

    override fun resolveDaggerDependency() {
        DaggerDetailComponent.builder()
            .applicationComponent(applicationComponent)
            .detailModule(DetailModule(this))
            .build().inject(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        NavigationUtils.animate(this, NavigationUtils.Animation.BACK)
    }

    //--------------------------------------------------
    // Activity Lifecycle
    //--------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repoName = mPresenter.getExtras()
        setCustomToolbar(true, repoName)
        mPresenter.initDataSet(mApiService, AppConfiguration.TEST_LOGIN, repoName)
    }

    //--------------------------------------------------
    // Menu
    //--------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
