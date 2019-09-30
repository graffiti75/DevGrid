package br.cericatto.devgrid.view.activity.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.User
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.api.OkHttpProvider
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * RetrofitActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 28, 2019
 */
class RetrofitActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConfiguration.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpProvider.instance)
            .build()

        val githubService = retrofit.create(ApiService::class.java)
        githubService.getUser(AppConfiguration.TEST_GITHUB_USER).enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                github_user_name.text = getString(R.string.error_loading_user)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    github_user_name.text = response.body()?.login
                } else {
                    github_user_name.text = getString(R.string.error_loading_user)
                }
            }
        })
    }
}
