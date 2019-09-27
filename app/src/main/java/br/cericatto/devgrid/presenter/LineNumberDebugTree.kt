package br.cericatto.devgrid.presenter

import timber.log.Timber

/**
 * LineNumberDebugTree.java.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
class LineNumberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}