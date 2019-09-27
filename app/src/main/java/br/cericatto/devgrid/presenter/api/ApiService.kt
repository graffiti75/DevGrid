package br.cericatto.devgrid.presenter.api

import br.cericatto.devgrid.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic Z3JhZmZpdGk3NTp6NHBwNDE5NzRBcG9zdHJvcGhlKg=="
    )
    @GET("/user/repos")
    fun getRepos(): Call<List<Repo>>
}