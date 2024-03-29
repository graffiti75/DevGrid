package br.cericatto.devgrid.presenter.presenter.impl

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.MainApplication
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.extensions.openActivity
import br.cericatto.devgrid.presenter.extensions.showToast
import br.cericatto.devgrid.presenter.getHeaderAuthentication
import br.cericatto.devgrid.presenter.presenter.MainPresenter
import br.cericatto.devgrid.view.activity.LoginActivity
import br.cericatto.devgrid.view.activity.MainActivity
import br.cericatto.devgrid.view.adapter.RepoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

/**
 * MainPresenterImpl.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
class MainPresenterImpl @Inject constructor(private val mActivity: MainActivity): MainPresenter {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private lateinit var mAdapter: RepoAdapter
    private lateinit var mService : ApiService
    private lateinit var mLogin: String
    private lateinit var mPassword: String

    //--------------------------------------------------
    // Override Methods
    //--------------------------------------------------

    override fun getExtras(): Pair<String, String> {
        val extras = mActivity.intent.extras
        var login = ""
        var password = ""
        if (extras != null) {
            login = extras.getString(AppConfiguration.QRCODE_LOGIN_EXTRA)
            password = extras.getString(AppConfiguration.USER_PASSWORD_EXTRA)
        }
        val app: MainApplication = mActivity.application as MainApplication
        app.password = password
        return Pair(login, password)
    }

    override fun initRecyclerView() {
        mAdapter = RepoAdapter(mActivity, this)
        mActivity.id_activity_main__recycler_view.adapter = mAdapter
        mActivity.id_activity_main__recycler_view.layoutManager = LinearLayoutManager(mActivity)
    }

    override fun initDataSet(context: MainActivity, service : ApiService, login: String, password: String) {
        val app: MainApplication = context.application as MainApplication
        val page = app.page
        if (page == 1) {
            mService = service
            mLogin = login
            mPassword = password
        }

        val observable = service.getRepos(
            login.getHeaderAuthentication(password), page)
        val subscription = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        showData(it)
                        Timber.i("getRepos() -> $it")
                    } else {
                        app.loadedAllData = true
                    }
                },
                {
                    it.message?.let { errorMessage ->
                        showErrorMessage(errorMessage)
                        context.showToast(context.getString(R.string.activity_login__authentication_error))
                        backToLoginScreen(context)
                    }
                },
                // OnCompleted
                {
                    Timber.i("getRepos() -> At OnCompleted.")
                }
            )
        val composite = CompositeDisposable()
        composite.add(subscription)
    }

    override fun showData(repos: MutableList<Repo>) {
        updateAdapter(repos)
        Timber.d(repos.toString())
    }

    override fun showErrorMessage(error: String) {
        Timber.e(error)
    }

    //--------------------------------------------------
    // Public Methods
    //--------------------------------------------------

    fun initDataSet() {
        initDataSet(mActivity, mService, mLogin, mPassword)
    }

    fun callLoading() {
        mActivity.id_activity_main__loading.visibility = View.VISIBLE
    }

    //--------------------------------------------------
    // Private Methods
    //--------------------------------------------------

    private fun updateAdapter(repos: MutableList<Repo>) {
        mActivity.id_activity_main__loading.visibility = View.GONE
        mActivity.id_activity_main__recycler_view.visibility = View.VISIBLE

        mAdapter.updateAdapter(repos)
    }

    private fun backToLoginScreen(context: MainActivity) {
        context.openActivity(context, LoginActivity::class.java)
    }
}