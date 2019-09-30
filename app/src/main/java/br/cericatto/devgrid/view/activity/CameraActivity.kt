package br.cericatto.devgrid.view.activity

import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import br.cericatto.devgrid.AppConfiguration
import br.cericatto.devgrid.R
import br.cericatto.devgrid.presenter.extensions.openActivityExtras
import br.cericatto.devgrid.view.activity.base.BaseActivity
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import kotlinx.android.synthetic.main.activity_camera.*

/**
 * CameraActivity.kt.
 *
 * @author Rodrigo Cericatto
 * @since September 30, 2019
 */
class CameraActivity : BaseActivity(), QRCodeReaderView.OnQRCodeReadListener {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

//    private var mBarcodeDetector: BarcodeDetector? = null
//    private var mCameraSource: CameraSource? = null

    //--------------------------------------------------
    // Base Activity
    //--------------------------------------------------

    override val contentView: Int
        get() = R.layout.activity_camera

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
        setCustomToolbar(false)
        initQrCodeRead()
    }

    override fun onResume() {
        super.onResume()
        id_qr_code__surface_view.startCamera()
    }

    override fun onPause() {
        super.onPause()
        id_qr_code__surface_view.stopCamera()
    }

    /*
    override fun onDestroy() {
        super.onDestroy()
        mCameraSource!!.release()
        mBarcodeDetector!!.release()
    }
    */

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun initQrCodeRead() {
//        initBarcodeDetector()
//        initCameraSource()
//        initSurfaceView()
//        callBarcodeDetectorProcessor()

        // TODO: Reminder -> https://github.com/dlazaro66/QRCodeReaderView

        id_qr_code__surface_view.setOnQRCodeReadListener(this)

        // Use this function to enable/disable decoding.
        id_qr_code__surface_view.setQRDecodingEnabled(true)

        // Use this function to change the autofocus interval (default is 5 secs).
        id_qr_code__surface_view.setAutofocusInterval(2000L)

        // Use this function to enable/disable Torch.
//        id_qr_code__surface_view.setTorchEnabled(true)

        // Use this function to set front camera preview.
//        id_qr_code__surface_view.setFrontCamera()

        // Use this function to set back camera preview.
        id_qr_code__surface_view.setBackCamera()

        id_qr_code__surface_view.startCamera()
    }

    /*
    private fun initBarcodeDetector() {
        mBarcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
    }

    private fun initCameraSource() {
        mCameraSource = CameraSource.Builder(this, mBarcodeDetector!!)
            .setRequestedPreviewSize(1600, 1024)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()
    }

    private fun initSurfaceView() {
        id_qr_code__surface_view!!.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    mCameraSource!!.start(id_qr_code__surface_view!!.holder)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {
                mCameraSource!!.stop()
            }
        })
    }

    private fun callBarcodeDetectorProcessor() {
        mBarcodeDetector!!.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}
            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() != 0) {
                    id_qr_code_launcher__text_view!!.post {
                        // Update barcode value to TextView.
                        id_qr_code_launcher__text_view!!.text = barcodes.valueAt(0).displayValue
                    }
                }
            }
        })
    }
    */

    //--------------------------------------------------
    // Listeners
    //--------------------------------------------------

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        id_qr_code_launcher__text_view.text = text
        text?.let {
            openActivityExtras(this, MainActivity::class.java,
                AppConfiguration.QRCODE_LOGIN_EXTRA, it
            )
        }
    }
}