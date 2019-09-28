package br.cericatto.devgrid.presenter.api

import okhttp3.OkHttpClient

/**
 * OkHttpProvider.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 28, 2019
 */
object OkHttpProvider {
    val instance: OkHttpClient = OkHttpClient.Builder().build()
}