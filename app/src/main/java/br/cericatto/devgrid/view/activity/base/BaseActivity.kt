package br.cericatto.devgrid.view.activity.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.cericatto.devgrid.MainApplication
import br.cericatto.devgrid.presenter.di.component.ApplicationComponent
import kotlinx.android.synthetic.main.toolbar.*

/**
 * BaseActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 26, 2019
 */
abstract class BaseActivity : AppCompatActivity() {

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    protected abstract val contentView: Int

    var applicationComponent: ApplicationComponent? = null

    @CallSuper
    protected open fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        applicationComponent = (application as MainApplication).applicationComponent
        resolveDaggerDependency()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewReady(savedInstanceState, intent)
    }

    //--------------------------------------------------
    // Dependency Injection
    //--------------------------------------------------

    protected open fun resolveDaggerDependency() {}

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    fun setCustomToolbar(homeEnabled: Boolean) {
        setSupportActionBar(id_toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
    }

    fun setCustomToolbar(homeEnabled: Boolean, title: String) {
        setSupportActionBar(id_toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
        supportActionBar?.title = title
    }
}