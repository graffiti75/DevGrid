package br.cericatto.devgrid.presenter.presenter.impl

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.MainApplication
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.commit.GithubCommit
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.extensions.showToast
import br.cericatto.devgrid.presenter.getHeaderAuthentication
import br.cericatto.devgrid.presenter.presenter.DetailPresenter
import br.cericatto.devgrid.view.activity.DetailActivity
import br.cericatto.devgrid.view.adapter.CommitAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import timber.log.Timber
import javax.inject.Inject

/**
 * DetailPresenterImpl.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
class DetailPresenterImpl @Inject constructor(private val mActivity: DetailActivity): DetailPresenter {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private lateinit var mAdapter: CommitAdapter
    private var mData : MutableList<GithubCommit> = mutableListOf()

    //--------------------------------------------------
    // Override Methods
    //--------------------------------------------------

    override fun getExtras(): String {
        val extras = mActivity.intent.extras
        if (extras != null) return extras.getString(AppConfiguration.REPO_NAME_EXTRA)
        return ""
    }

    override fun initDataSet(service : ApiService, userLogin: String, password: String, repoName: String) {
        val observable = service.getCommits(
            userLogin.getHeaderAuthentication(password), userLogin, repoName)
        val subscription = observable
            .subscribeOn(Schedulers.io())
            // Be notified on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { it.take(AppConfiguration.COMMITS_NUMBER) }
            .subscribe(
                {
                    concatData(it)
                    Timber.i("getCommits() -> $it")
                },
                {
                    it.message?.let { errorMessage ->
                        showErrorMessage(errorMessage)
                        mActivity.showToast(mActivity.getString(R.string.activity_detail__authentication_error))
                        mActivity.finish()
                    }
                },
                // OnCompleted
                {
                    showData()
                }
            )
        val composite = CompositeDisposable()
        composite.add(subscription)
    }



    override fun showData() {
        if (mData.isEmpty()) setEmptyTextView()
        else setAdapter(mData)
    }

    override fun showErrorMessage(error: String) {
        Timber.e(error)
    }

    //--------------------------------------------------
    // Private Methods
    //--------------------------------------------------

    private fun concatData(data: GithubCommit) {
        mData.add(data)
    }

    private fun setAdapter(commits: List<GithubCommit>) {
        mActivity.id_activity_detail__loading.visibility = View.GONE
        mActivity.id_activity_detail__recycler_view.visibility = View.VISIBLE
        mActivity.id_activity_detail__empty_text_view.visibility = View.GONE

        mAdapter = CommitAdapter(mActivity, commits)
        mActivity.id_activity_detail__recycler_view.adapter = mAdapter
        mActivity.id_activity_detail__recycler_view.layoutManager = LinearLayoutManager(mActivity)
    }

    private fun setEmptyTextView() {
        mActivity.id_activity_detail__loading.visibility = View.GONE
        mActivity.id_activity_detail__recycler_view.visibility = View.GONE
        mActivity.id_activity_detail__empty_text_view.visibility = View.VISIBLE
    }
}
