package br.cericatto.devgrid.view.activity.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.R
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.api.OkHttpProvider
import br.cericatto.devgrid.presenter.getHeaderAuthentication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * TestActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 28, 2019
 */
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConfiguration.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpProvider.instance)
            .build()

        /*
        val githubService = retrofit.create(ApiService::class.java)
        githubService.getRepos(AppConfiguration.getHeaderAuthentication()).enqueue(object: Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                github_user_id.text = t.message
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) {
                    val repo = response.body()
                    github_user_id.text = repo!![0].id
                }
            }
        })
         */
        val service = retrofit.create(ApiService::class.java)
        val observable = service.getRepos(
            AppConfiguration.getHeaderAuthentication(), 1)
        val subscription = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    github_user_id.text = it[0].id
                },
                {
                    github_user_id.text = it.message
                },
                // OnCompleted
                {}
            )
        val composite = CompositeDisposable()
        composite.add(subscription)
    }
}