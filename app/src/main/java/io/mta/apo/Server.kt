package io.mta.apo

import android.util.Log
import java.io.File
import java.io.IOException
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.Call
import okhttp3.Callback
import org.json.JSONObject
import org.json.JSONStringer
import java.net.URLEncoder

/**
 * From jgonzalezcastello:
 * https://github.com/jgonzalezcastello/Apo/blob/pill_search_form/app/src/main/java/io/mta/apo/Server.kt
 */
class Server {

    // API constants
    val SERVER_URL = "http://165.227.30.127/"
    val API = "api/"
    val API_VERSION = "1/"
    val PILL="pill/"
    val SEARCH = "search?"

    // query constants
    val MEDICINE_NAME = "medicine_name="
    val SHAPE = "shape="
    val IMPRINT = "imprint="
    val COLOR = "color="

    // okhttp constants
    val JSON:MediaType = MediaType.parse("application/json; charset=utf-8")!! // converts nullable to non-nullable, throws nullpointererror is null
    var client:OkHttpClient = OkHttpClient()

    // urlencoding
    val UTF8 = "UTF-8"

    /**
     * Returns the whole url to the current version of the API
     */
    fun getAPIUrl(): String {
        return SERVER_URL + API + API_VERSION
    }

    /**
     * Returns the whole url to the current version of the pill endpoint
     * @return a url for the pill endpoint
     */
    fun getPillEndpoint(): String {
        return SERVER_URL + API + API_VERSION + PILL + SEARCH
    }

    // TODO: pass query object not user input
    fun queryServer(shape:String, imprint:String, color:String): String {
        Log.i( "Server::queryServer", "queryServer called" )

        val json: JSONObject = JSONObject()
        json.put(SHAPE, shape)
        json.put(IMPRINT, imprint)
        json.put(COLOR, color)
        val request_body: RequestBody = RequestBody.create(JSON, json.toString())
        val request: Request = Request.Builder()
                .url(getAPIUrl())
                .build()
        val response = client.newCall(request).execute()

        return response.body()!!.string()
    }

    /**Constructs url to search for pill with specified parameters
     * @param {string} name - The name associated with the pill
     * @param {string} imprint - The imprint on the pill
     * @param {string} color - The color on the pill
     */
    fun getPillSearchUrl(name:String = "",imprint: String = "",color: String = ""):String{
        var url = getPillEndpoint()
        if(!name.isEmpty()){
            url += MEDICINE_NAME + URLEncoder.encode(name, UTF8)
        }
        if(!imprint.isEmpty()){
            url += IMPRINT + URLEncoder.encode(imprint, UTF8)
        }
        if(!color.isEmpty()){
            url += COLOR + URLEncoder.encode(color, UTF8)
        }
        Log.i("Server:getPillSearchURL","URL Built: "+url)
        return url;
    }

    /**Submits an information request to the data server
     * @param {string} name - The name associated with the pill
     * @param {string} imprint - The imprint on the pill
     * @param {string} color - The color on the pill
     */
    fun queryServerPillOption(name:String = "", imprint:String = "", color:String = ""): Array<Pill>{
        Log.i("Server::queryPillServer","queryPillServer called")

        val request: Request = Request.Builder()
                .url(getPillSearchUrl(name,imprint,color))
                .build()
        val response = client.newCall(request).execute()

        val json = response.body()!!.string()
        return PillJsonAdapter.fromJsonArray(json)
    }

}