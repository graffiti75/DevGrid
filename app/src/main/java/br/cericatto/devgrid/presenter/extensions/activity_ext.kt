package br.cericatto.devgrid.presenter.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import br.cericatto.devgrid.presenter.NavigationUtils

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showToast(message: Int) {
    Toast.makeText(this, this.getString(message), Toast.LENGTH_LONG).show()
}

fun Context.openActivity(activity: Activity, clazz: Class<*>) {
    val intent = Intent(activity, clazz)
    activity.startActivity(intent)
    NavigationUtils.animate(activity, NavigationUtils.Animation.GO)
}

fun Context.openActivityExtra(activity: Activity, clazz: Class<*>, key: String, value: Any) {
    val intent = Intent(activity, clazz)
    val extras = getExtra(Bundle(), key, value)
    intent.putExtras(extras)

    activity.startActivity(intent)
    NavigationUtils.animate(activity, NavigationUtils.Animation.GO)
}

fun Context.openActivityExtras(activity: Activity, clazz: Class<*>, keys: Array<String>, values: Array<Any>) {
    val intent = Intent(activity, clazz)
    var extras = Bundle()
    val size = keys.size
    for (i in 0 until size) {
        val key = keys[i]
        val value = values[i]
        extras = getExtra(extras, key, value)
    }
    intent.putExtras(extras)

    activity.startActivity(intent)
    NavigationUtils.animate(activity, NavigationUtils.Animation.GO)
}

fun Context.getExtra(extras: Bundle, key: String, value: Any): Bundle {
    when (value) {
        is String -> extras.putString(key, value)
        is Int -> extras.putInt(key, value)
        is Long -> extras.putLong(key, value)
        is Boolean -> extras.putBoolean(key, value)
    }
    return extras
}