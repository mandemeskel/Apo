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

/**
 * Created by guest on 10/4/17.
 */
class Server {

    // API constants
    val SERVER_URL = "http://mata.io/"
    val API = "api/"
    val API_VERSION = "1"

    // query constants
    val SHAPE = "shape"
    val IMPRINT = "imprint"
    val COLOR = "color"

    // okhttp constants
    val JSON:MediaType = MediaType.parse("application/json; charset=utf-8")!! // converts nullable to non-nullable, throws nullpointererror is null
    var client:OkHttpClient = OkHttpClient()

    /**
     * Returns the whole url to the current version of the API
     */
    fun getAPIUrl(): String {
        return SERVER_URL + API + API_VERSION
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
                .post(request_body)
                .build()
        val response = client.newCall(request).execute()

        return response.body()!!.string()
    }

}