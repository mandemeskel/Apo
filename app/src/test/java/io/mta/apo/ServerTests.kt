package io.mta.apo

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.net.URLEncoder

class ServerTests {    val server = Server()

    val TAG = "ServerTests"
    val pill_name = "Advil PM"
    val pill_imprint = "Advil"
    val pill_color = "BLUE"
    val utf8_name = URLEncoder.encode(pill_name, server.UTF8)
    val utf8_imprint = URLEncoder.encode(pill_imprint, server.UTF8)
    val utf8_color = URLEncoder.encode(pill_color, server.UTF8)

    @Test
    fun getPillSearchUrl() {
        val expected_url = server.getPillEndpoint() + server.MEDICINE_NAME + utf8_name + server.IMPRINT + utf8_imprint + server.COLOR + utf8_color

        val url = server.getPillSearchUrl(pill_name, pill_imprint, pill_color)
        assertThat("getPillSearchUrl should return a properly formated url", expected_url == url)
    }

    @Test
    fun queryServerPillOption() {
        var url = server.getPillSearchUrl(pill_name, "", "")
        System.out.println(url)
        val data = server.queryServerPillOption(pill_name, "", "")
        assertThat("queryServerPillOption returns a JSON string", data is String)
    }
}
