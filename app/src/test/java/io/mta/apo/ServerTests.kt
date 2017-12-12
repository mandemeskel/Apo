package io.mta.apo

import org.junit.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class ServerTests {
    val server = Server()

    @Test
    fun getPillSearchUrl() {
        // TODO: should return the correct url
    }

    @Test
    fun queryServerPillOption() {
        // TODO: should return list of pills
    }
}
