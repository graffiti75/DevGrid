package br.cericatto.devgrid.presenter.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpProvider {
    private var instance: OkHttpClient? = null

    val okHttpInstance: OkHttpClient
        get() {
            if (instance == null) {
                instance = OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.SECONDS)
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .build()
            }
            return instance!!
        }
}