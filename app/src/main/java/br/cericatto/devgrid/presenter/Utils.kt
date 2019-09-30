package br.cericatto.devgrid.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import br.cericatto.devgrid.AppConfiguration

/**
 * Utils.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 28, 2019
 */
fun AppConfiguration.getHeaderAuthentication(): String {
    val base64 = "${this.TEST_LOGIN}:${this.TEST_PASSWORD}".encodeBase64ToString()
    return "Basic $base64"
}

fun String.getHeaderAuthentication(password: String): String {
    var login = getLoginFromUrl()
    val base64 = "${login}:${password}".encodeBase64ToString()
    return "Basic $base64"
}

fun String.getLoginFromUrl(): String {
    val parts = this.split("/")
    return parts[parts.size - 1]
}

/*
    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
     */

@Suppress("DEPRECATION")
fun Context.checkIfHasNetwork(): Boolean {
    var result = false
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                }
            }
        }
    }
    return result
}