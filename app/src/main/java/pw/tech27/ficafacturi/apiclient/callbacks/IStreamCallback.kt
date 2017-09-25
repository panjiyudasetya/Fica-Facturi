package pw.tech27.ficafacturi.apiclient.callbacks

import java.io.InputStream

/**
 * Created by panjiyudasetya on 9/25/17.
 */

interface IStreamCallback {
    fun onSuccess(stream: InputStream?)
    fun onFailure(message: String?)
}
