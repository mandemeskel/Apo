package io.mta.apo

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.net.URLEncoder
import okhttp3.OkHttpClient
import okhttp3.Request

class ServerTests {

    val TAG = "ServerTests"
    val server = Server()
    val pill_name = "Advil PM"
    val pill_imprint = "Advil"
    val pill_color = "BLUE"
    val utf8_name = URLEncoder.encode(pill_name, server.UTF8)
    val utf8_imprint = URLEncoder.encode(pill_imprint, server.UTF8)
    val utf8_color = URLEncoder.encode(pill_color, server.UTF8)
    var client:OkHttpClient = OkHttpClient()

    @Test
    fun getPillSearchUrl() {
        val expected_url = server.getPillEndpoint() + server.MEDICINE_NAME + utf8_name + server.IMPRINT + utf8_imprint + server.COLOR + utf8_color

        val url = server.getPillSearchUrl(pill_name, pill_imprint, pill_color)
        assertThat("getPillSearchUrl should return a properly formated url", expected_url == url)
    }

    @Test
    fun queryServerPillOptionReturnsPillArray() {
        val data = server.queryServerPillOption(pill_name)
        assertThat("queryServerPillOption should return an array of pills", data is Array<Pill>)
    }

    @Test
    fun queryServerPillOptionReturnsAllPills() {
        val url = server.getPillSearchUrl(pill_name)
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        val json = response.body()!!.string()
        val num_objects = json.split("},{").size

        val data = server.queryServerPillOption(pill_name)
        assertThat("queryServerPillOption should return an array of all the pills returned by the query", data.size == num_objects)
    }


    @Test
    fun queryServerPillOptionReturnsInitializedPills() {
        val url = server.getPillSearchUrl(pill_name)
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        val json = response.body()!!.string()

        val pills = server.queryServerPillOption(pill_name)
        // I chose to test these particular fields because all pills in the
        // database have a value for these fields
        for(pill in pills) {
            assertThat("queryServerPillOption should return pills with ids", json.contains(pill.id.toString()))

            assertThat("queryServerPillOption should return pills with brand names", pill.brand_name.isNotBlank())

            assertThat("queryServerPillOption should return pills with medical names", pill.medical_name.isNotBlank())

            assertThat("queryServerPillOption should return pills with descriptions", pill.description.isNotBlank())
        }
    }
}
