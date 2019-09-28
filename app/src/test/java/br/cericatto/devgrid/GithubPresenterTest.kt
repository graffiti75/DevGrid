package br.cericatto.devgrid

import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.presenter.GithubPresenter
import br.cericatto.devgrid.presenter.GithubPresenterImpl
import br.cericatto.devgrid.presenter.api.ApiService
import br.cericatto.devgrid.view.MainActivity
import com.nhaarman.mockitokotlin2.mock
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * GithubPresenterTest.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
class GithubPresenterTest {
    // https://caster.io/lessons/retrofit-2-unit-testing-mockito

    lateinit var reposPresenterImpl: GithubPresenterImpl
    lateinit var repos: List<Repo>

    @Mock
    internal var presenter: GithubPresenter? = null

    val apiService = mock<ApiService>()
//    @Mock
//    internal var apiService: ApiService? = null

    val mockCall = mock<Call<List<Repo>>>()
//    @Mock
//    internal var mockCall: Call<List<Repo>>? = null

    val responseBody = mock<ResponseBody>()
//    @Mock
//    internal var responseBody: ResponseBody? = null

    @Captor
    lateinit var argumentCapture: ArgumentCaptor<Callback<List<Repo>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        reposPresenterImpl = GithubPresenterImpl(MainActivity())
        repos = listOf(Repo())
    }

    /*
    @Test
    fun initDataSet_shouldGetRepos() {
        `when`(apiService!!.getRepos()).thenReturn(mockCall)
        reposPresenterImpl!!.initDataSet(apiService)

        verify<Call<List<Repo>>>(mockCall).enqueue(argumentCapture!!.capture())
        val res = Response.success(repos)
        argumentCapture!!.value.onResponse(mockCall, res)

        presenter!!.showData(repos)
    }

    @Test
    fun initDataSet_shouldDoNothing_whenBadRequest() {
        `when`(apiService!!.getRepos()).thenReturn(mockCall)
        reposPresenterImpl!!.initDataSet(apiService)

        verify<Call<List<Repo>>>(mockCall).enqueue(argumentCapture!!.capture())
        val res = Response.error<List<Repo>>(500, responseBody!!)
        argumentCapture!!.value.onResponse(null!!, res)

        verifyZeroInteractions(presenter)
    }

    @Test
    fun initDataSet_shouldShowError_whenFailedRequest() {
        `when`(apiService!!.getRepos()).thenReturn(mockCall)
        reposPresenterImpl!!.initDataSet(apiService)

        verify<Call<List<Repo>>>(mockCall).enqueue(argumentCapture!!.capture())
        val throwable = Throwable(RuntimeException())
        argumentCapture!!.value.onFailure(null!!, throwable)

        presenter!!.showErrorMessage()
    }
    */
}