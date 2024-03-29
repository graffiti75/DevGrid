package br.cericatto.devgrid.presenter

import android.util.Log
import timber.log.Timber

/**
 * ReleaseTree.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {}

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) return false
        return true // Only log WARN, ERROR, WTF
    }
}