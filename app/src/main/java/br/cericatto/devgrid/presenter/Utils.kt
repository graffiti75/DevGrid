package br.cericatto.devgrid.presenter

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



