package pw.tech27.ficafacturi.apiclient

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import pw.tech27.ficafacturi.apiclient.callbacks.IStreamCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by panjiyudasetya on 9/25/17.
 */
class FicafacturiClient private constructor() {
    init { }
    private object Holder { val INSTANCE = FicafacturiClient() }
    companion object {
        val instance: FicafacturiClient by lazy { Holder.INSTANCE }

        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        private val apiClient: FicafacturiService = Retrofit
                .Builder()
                .baseUrl("http://belikebill.azurewebsites.net")
                .client(client)
                .build()
                .create(FicafacturiService::class.java)
    }

    open fun fetch(callback: IStreamCallback) {
        apiClient.fetch().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response != null) {
                    if (response.isSuccessful) callback.onSuccess(response.body()?.byteStream())
                    else callback.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                callback.onFailure(t?.message)
            }
        })
    }
}