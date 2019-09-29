package br.cericatto.devgrid.presenter

import androidx.recyclerview.widget.LinearLayoutManager
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.view.activity.MainActivity
import br.cericatto.devgrid.view.adapter.RepoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

/**
 * GithubPresenterImpl.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
class GithubPresenterImpl @Inject constructor(private val mActivity: MainActivity): GithubPresenter {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private lateinit var mAdapter: RepoAdapter

    //--------------------------------------------------
    // Override Methods
    //--------------------------------------------------

    override fun initDataSet(service : ApiService) {
        /*
        service.getRepos(AppConfiguration.getHeaderAuthentication()).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) {
                    showData(response.body()!!)
                    Timber.i("Repo data was loaded from API.")
                }
            }
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                showErrorMessage()
                Timber.e(t, "Unable to load Repo data from API.")
            }
        })
        */
        val observable = service.getRepos(AppConfiguration.getHeaderAuthentication())
        val subscription = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showData(it)
                    Timber.i("Repo data was loaded from API.")
                },
                {
                    showErrorMessage()
                    Timber.e(it, "Unable to load Repo data from API.")
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

    override fun showErrorMessage() {
//        mActivity.id_text_view.text = "Error in Retrofit."
        Timber.e("Error in Retrofit.")
    }

    //--------------------------------------------------
    // Private Methods
    //--------------------------------------------------

    private fun setAdapter(repos: List<Repo>) {
        mAdapter = RepoAdapter(mActivity, repos)
        mActivity.id_repos__recycler_view.adapter = mAdapter
        mActivity.id_repos__recycler_view.layoutManager = LinearLayoutManager(mActivity)
    }
}