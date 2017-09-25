package pw.tech27.ficafacturi.presenter

import android.graphics.Bitmap

/**
 * Created by panjiyudasetya on 9/25/17.
 */
interface FicaFacturiContract {
    interface View {
        fun clearImageView()
        fun onReceived(bitmap: Bitmap?)
        fun onReceivedAnError(message: String?)
    }
    interface Presenter : BasePresenter
}