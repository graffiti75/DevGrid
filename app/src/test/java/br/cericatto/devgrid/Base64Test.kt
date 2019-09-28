package br.cericatto.devgrid

import br.cericatto.devgrid.presenter.decodeBase64
import br.cericatto.devgrid.presenter.encodeBase64ToString
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Base64Test.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 28, 2019
 */
class Base64Test {

    @Test
    fun testBase64Authorization() {
        val text = "${AppConfiguration.TEST_LOGIN}:${AppConfiguration.TEST_PASSWORD}"
        val encode= encoder(text)
        val decode = decoder(encode)
        assertEquals(text, decode)
    }

    private fun encoder(entry: String): String {
        return entry.encodeBase64ToString()
    }

    private fun decoder(entry: String): String {
        return entry.decodeBase64()
    }
}