package br.cericatto.devgrid.presenter.presenter.impl

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.getHeaderAuthentication
import br.cericatto.devgrid.presenter.presenter.MainPresenter
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

    //--------------------------------------------------
    // Override Methods
    //--------------------------------------------------

    override fun getExtras(): String {
        val extras = mActivity.intent.extras
        if (extras != null) return extras.getString(AppConfiguration.QRCODE_LOGIN_EXTRA)
        return ""
    }

    override fun initDataSet(service : ApiService, login: String, password: String) {
        val observable = service.getRepos(login.getHeaderAuthentication(password))
        val subscription = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showData(it)
                    Timber.i("getRepos() -> $it")
                },
                {
                    it.message?.let { errorMessage -> showErrorMessage(errorMessage) }
                },
                // OnCompleted
                {}
            )
        val composite = CompositeDisposable()
        composite.add(subscription)
    }

    override fun showData(repos: List<Repo>) {
        setAdapter(repos)
        Timber.d(repos.toString())
    }

    override fun showErrorMessage(error: String) {
        Timber.e(error)
    }

    //--------------------------------------------------
    // Private Methods
    //--------------------------------------------------

    private fun setAdapter(repos: List<Repo>) {
        mActivity.id_main__loading.visibility = View.GONE
        mActivity.id_repos__recycler_view.visibility = View.VISIBLE

        mAdapter = RepoAdapter(mActivity, repos)
        mActivity.id_repos__recycler_view.adapter = mAdapter
        mActivity.id_repos__recycler_view.layoutManager = LinearLayoutManager(mActivity)
    }
}