package io.mta.apo

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
//import kotlin.assert

/**
 * Created by guest on 10/4/17.
 */
//@RunWith(JUnitPlatform::class)
object ServerTests: Spek({

    given("a test") {
        val server = Server()

        on("getAPIUrl") {
            val url = server.getAPIUrl()
            val server_url = "http://mata.io/api/1"
            it("should return the complete url to the website's api endpoint") {
                assert(server_url == url)
            }
            assertEquals("", url)
        }
    }

})
