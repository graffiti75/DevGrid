package br.cericatto.devgrid.view

import android.content.Intent
import android.os.Bundle
import br.cericatto.devgrid.R
import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.presenter.di.component.DaggerTestComponent
import br.cericatto.devgrid.presenter.di.module.TestModule
import kotlinx.android.synthetic.main.activity_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TestActivity : BaseActivity() {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    @Inject
    lateinit var mApiService: ApiService

    //--------------------------------------------------
    // Base Activity
    //--------------------------------------------------

    override val contentView: Int
        get() = R.layout.activity_test

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        setContentView(contentView)
        super.onViewReady(savedInstanceState, intent)
    }

    override fun resolveDaggerDependency() {
        DaggerTestComponent.builder()
            .applicationComponent(applicationComponent)
            .testModule(TestModule())
            .build().inject(this)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mApiService.getRepos().enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) {
                    val repo = response.body()
                    text_view.text = repo!![0].id
                }
            }
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                text_view.text = t.message
            }
        })
    }
}