package br.cericatto.devgrid.view.activity

import android.content.Intent
import android.os.Bundle
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.MainApplication
import br.cericatto.devgrid.R
import br.cericatto.devgrid.presenter.extensions.openActivityExtras
import br.cericatto.devgrid.presenter.extensions.showToast
import br.cericatto.devgrid.presenter.getLoginFromUrl
import br.cericatto.devgrid.view.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * LoginActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 30, 2019
 */
class LoginActivity : BaseActivity() {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private lateinit var mLogin: String
    private lateinit var mPassword: String

    //--------------------------------------------------
    // Base Activity
    //--------------------------------------------------

    override val contentView: Int
        get() = R.layout.activity_login

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        setContentView(contentView)
        super.onViewReady(savedInstanceState, intent)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    //--------------------------------------------------
    // Activity Lifecycle
    //--------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomToolbar(true)
        getLogin()
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun getLogin() {
        mLogin = getExtras().getLoginFromUrl()
        activity_login__login_text_view.text = getString(R.string.activity_login__info, mLogin)
        val app : MainApplication = application as MainApplication
        app.login = mLogin

        activity_login__signup_button.setOnClickListener {
            performLogin()
        }
    }

    private fun getExtras(): String {
        val extras = intent.extras
        if (extras != null) return extras.getString(AppConfiguration.QRCODE_LOGIN_EXTRA)
        return ""
    }

    private fun performLogin() {
        mPassword = activity_login__password_edit_text.editableText.toString()
        if (mPassword.isNotEmpty())
            openActivityExtras(this, MainActivity::class.java,
                arrayOf(AppConfiguration.QRCODE_LOGIN_EXTRA, AppConfiguration.USER_PASSWORD_EXTRA),
                arrayOf(mLogin.getLoginFromUrl(), mPassword))
        else activity_login__text_input_layout.error = getString(R.string.activity_login__empty_password)
    }
}
