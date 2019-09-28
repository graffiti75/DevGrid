package br.cericatto.devgrid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.api.OkHttpProvider
import br.cericatto.devgrid.presenter.getHeaderAuthentication
import kotlinx.android.synthetic.main.activity_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    }
}