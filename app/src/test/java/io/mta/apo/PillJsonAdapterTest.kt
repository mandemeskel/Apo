package io.mta.apo

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Tests for the PillJsonAdpater class
 */
class PillJsonAdapterTest {

    val adapter = PillJsonAdapter()
    val json = "{\"pillID\": 29465,\"shape\": \"OVAL\",\"rxString\": \"Diphenhydramine Citrate 38 MG / Ibuprofen 200 MG Oral Tablet [Advil PM]\",\"medicine_name\": \"Advil PM\",\"splimage\": \"\",\"active_ingredient\": [\"Ibuprofen 200 mg\", \"Diphenhydramine citrate 38 mg\", \"\"],\"color\": [\"BLUE\"],\"imprint\": [\"Advil\", \"PM\"]}"
    val pill_id = 2946
    val brand_name = "Advil PM"
    val medical_name = "Diphenhydramine Citrate 38 MG / Ibuprofen 200 MG Oral Tablet [Advil PM]"
    val img_path = ""
    val description = "active ingredients:\n Ibuprofen 200 mg' \n 'Diphenhydramine citrate 38 mg'"
    var pill: Pill? = null

    @Before
    fun parseJson() {
        pill = adapter.fromJson(json)
    }

    @Test
    fun fromJsonReturnsCorrectPillID() {
        assertThat("returned pill has the correct id", pill!!.id == pill_id)
    }

    @Test
    fun fromJsonReturnsCorrectBrandName() {
        assertThat("returned pill has the correct id", pill!!.brand_name == brand_name)
    }

    @Test
    fun fromJsonReturnsCorrectMedicalName() {
        assertThat("returned pill has the correct id", pill!!.medical_name == medical_name)
    }

    @Test
    fun fromJsonReturnsCorrectImagePath() {
        assertThat("returned pill has the correct id", pill!!.img_path == img_path)
    }

    @Test
    fun fromJsonReturnsCorrectDescription() {
        assertThat("returned pill has the correct id", pill!!.description == description)
    }

}