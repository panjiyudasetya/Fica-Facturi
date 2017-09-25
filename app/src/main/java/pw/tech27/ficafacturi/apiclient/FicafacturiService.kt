package pw.tech27.ficafacturi.apiclient

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by panjiyudasetya on 9/25/17.
 */
interface FicafacturiService {
    @GET("billgen-API.php?default=1")
    fun fetch() : Call<ResponseBody>
}