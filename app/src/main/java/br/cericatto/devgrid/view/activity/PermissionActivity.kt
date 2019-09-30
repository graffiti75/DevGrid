package br.cericatto.devgrid.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import br.cericatto.devgrid.presenter.extensions.openActivity
import br.cericatto.devgrid.view.activity.base.BaseActivity

/**
 * PermissionActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 30, 2019
 */
class PermissionActivity : BaseActivity() {

    //--------------------------------------------------
    // Constants
    //--------------------------------------------------

    companion object {
        private const val CAMERA_REQUEST_CODE = 1001
    }

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private var mPermissionsAllowed = false

    //--------------------------------------------------
    // Base Activity
    //--------------------------------------------------

    override val contentView: Int
        get() = br.cericatto.devgrid.R.layout.activity_permission

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

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPermissionsAllowed = true
                initQrCodeRead()
            } else {
                requestPermissionProcess()
            }
        }
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun checkPermissions() {
        if (!mPermissionsAllowed) {
            requestPermissionProcess()
        } else {
            initQrCodeRead()
        }
    }

    private fun requestPermissionProcess() {
        val cameraRequestCode = CAMERA_REQUEST_CODE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), cameraRequestCode)
            return
        }
    }

    private fun initQrCodeRead() {
        openActivity(this, CameraActivity::class.java)
    }
}