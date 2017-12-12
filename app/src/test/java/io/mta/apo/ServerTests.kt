package io.mta.apo

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ServerTests {
    val pill_name = "Advil"
    val pill_imprint = "A"
    val pill_color = "white"
    val server = Server()

    @Test
    fun getPillSearchUrl() {
        val expected_url = server.getPillEndpoint() + server.MEDICINE_NAME + pill_name + server.IMPRINT + pill_imprint + server.COLOR + pill_color
        val url = server.getPillSearchUrl(pill_name, pill_imprint, pill_color)
        assertThat("getPillSearchUrl should return a properly formated url", expected_url == url)
    }

    @Test
    fun queryServerPillOption() {
        // TODO: should return list of pills
    }
}
