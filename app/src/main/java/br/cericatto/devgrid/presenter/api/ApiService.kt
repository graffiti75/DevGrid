package br.cericatto.devgrid.presenter.api

import br.cericatto.devgrid.model.Repo
import br.cericatto.devgrid.model.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * ApiService.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
interface ApiService {
//    @Headers(
//        "Content-Type: application/json",
//        "Authorization: Basic <base64>"
//    )
//    @GET("/user/repos")
//    fun getRepos(@Header("Authorization") authorization: String): Call<List<Repo>>


    @GET("/user/repos")
    fun getRepos(@Header("Authorization") authorization: String): Observable<List<Repo>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Call<User>
}