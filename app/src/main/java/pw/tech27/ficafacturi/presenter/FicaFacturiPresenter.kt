package pw.tech27.ficafacturi.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import pw.tech27.ficafacturi.apiclient.FicafacturiClient
import pw.tech27.ficafacturi.apiclient.callbacks.IStreamCallback
import java.io.InputStream

/**
 * Created by panjiyudasetya on 9/25/17.
 */

class FicaFacturiPresenter constructor(apiClient: FicafacturiClient, view: FicaFacturiContract.View) : FicaFacturiContract.Presenter {
    private var mApiClient: FicafacturiClient? = apiClient
    private var mView: FicaFacturiContract.View? = view
    private var mBitmap: Bitmap? = null

    override fun start() {
        mApiClient?.fetch(object : IStreamCallback {
            override fun onSuccess(stream: InputStream?) {
                if (stream == null) return
                mView?.clearImageView()

                reuseBitmap()
                mBitmap = BitmapFactory.decodeStream(stream)

                mView?.onReceived(mBitmap)
            }

            override fun onFailure(message: String?) {
                mView?.onReceivedAnError(message)
            }
        })
    }

    override fun stop() {
        mApiClient = null
        mView = null
    }

    private fun reuseBitmap() {
        if (mBitmap != null) {
            mBitmap?.recycle()
            mBitmap = null
        }
    }
}
