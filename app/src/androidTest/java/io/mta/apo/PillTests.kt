package io.mta.apo

import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith

/**
 * Created by Michael T. Andemeskel on 12/5/17.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class PillTests {
    val pill_brand_name = "advil"
    val pill_medical_name = "ibuprofem"
    val pill_img_path = "img/path/img.jpg"
    val pill_description = "wonder drug"
    val test_pill = Pill(pill_brand_name, pill_medical_name, pill_img_path, pill_description)
    val context = InstrumentationRegistry.getTargetContext()

    @Before
    fun save_test_pill() {
        // TODO
    }

    @After
    fun clear_saved_pills() {
        Pill.clearSavedPills(context)
    }

    @Test
    fun pill_can_be_saved_and_loaded() {
        val saved_pills = Pill.loadSavedPills(context)
        assertThat("loadSavedPills returns a list of pills that contains saved pill.", saved_pills.contains(test_pill.entity))
    }

    @Test
    fun saved_pills_can_be_cleared() {
        Pill.clearSavedPills(context)
        val saved_pills = Pill.loadSavedPills(context)
        assert(saved_pills.isEmpty() == true)
    }

}