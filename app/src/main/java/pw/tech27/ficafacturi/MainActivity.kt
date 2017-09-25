package pw.tech27.ficafacturi

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import pw.tech27.ficafacturi.apiclient.FicafacturiClient
import pw.tech27.ficafacturi.presenter.FicaFacturiContract
import pw.tech27.ficafacturi.presenter.FicaFacturiPresenter

class MainActivity : AppCompatActivity(), FicaFacturiContract.View {
    private var mPresenter: FicaFacturiPresenter? = null
    private var mIvPreview: ImageView? = null
    private var mBtnReload: Button? = null
    private var mContainer: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = FicaFacturiPresenter(FicafacturiClient.instance, this)

        mContainer = findViewById(R.id.container) as RelativeLayout
        mIvPreview = findViewById(R.id.iv_preview) as ImageView
        mBtnReload = findViewById(R.id.btn_reload) as Button

        mBtnReload?.setOnClickListener {
            mPresenter?.start()
        }

        mPresenter?.start()
    }

    override fun clearImageView() {
        mIvPreview?.setImageBitmap(null)
    }

    override fun onReceived(bitmap: Bitmap?) {
        mIvPreview?.setImageBitmap(bitmap)
    }

    override fun onReceivedAnError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.stop()
    }
}
