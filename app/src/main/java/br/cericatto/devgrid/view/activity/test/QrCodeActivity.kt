package br.cericatto.devgrid.view.activity.test

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.SparseArray
import br.cericatto.devgrid.R
import br.cericatto.devgrid.view.activity.base.BaseActivity
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_qrcode.*
import timber.log.Timber

/**
 * QrCodeActivity.kt.
 * Source: http://www.devexchanges.info/2016/10/reading-barcodeqr-code-using-mobile.html
 *
 * @author Rodrigo Cericatto
 * @since September 30, 2019
 */
class QrCodeActivity : BaseActivity() {

    //--------------------------------------------------
    // Base Activity
    //--------------------------------------------------

    override val contentView: Int
        get() = R.layout.activity_qrcode

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
        setCustomToolbar(false, getString(R.string.activity_qrcode))

        id_activity_qrcode__button.setOnClickListener {
            try {
                val barCodes = initBarCodeDetection()
                // Check if at least one barcode was detected.
                checkBarCodeDetection(barCodes)
            } catch (e: Exception) {
                Timber.e(e.message)
//                e.printStackTrace()
            }
        }
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private fun initBarCodeDetection(): SparseArray<Barcode> {
        val myQRCode = BitmapFactory.decodeStream(assets.open("qr_code.png"))
        val barcodeDetector = BarcodeDetector.Builder(this@QrCodeActivity)
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()
        val frame = Frame.Builder().setBitmap(myQRCode).build()
        return barcodeDetector.detect(frame)
    }

    private fun checkBarCodeDetection(barCodes: SparseArray<Barcode>) {
        if (barCodes.size() != 0) {
            // Display the QR code's message.
            id_activity_qrcode__text_view.text = "QR CODE Data: " + barCodes.valueAt(0).displayValue
            // Display QR code image to ImageView.
//            id_qr_code__image_view.setImageBitmap(myQRCode)
        } else {
            id_activity_qrcode__text_view.text = "No QR Code found!"
            id_activity_qrcode__text_view.setTextColor(Color.RED)
        }
    }
}