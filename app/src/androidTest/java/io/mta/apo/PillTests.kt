package io.mta.apo

import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by Michael T. Andemeskel on 12/5/17.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class PillTests {
    val TAG = "PillTests"
    val pill_id = 209
    val pill_brand_name = "advil"
    val pill_medical_name = "ibuprofem"
    val pill_img_path = "img/path/img.jpg"
    val pill_description = "wonder drug"
    val test_pill = Pill(pill_id, pill_brand_name, pill_medical_name, pill_img_path, pill_description)
    val context = InstrumentationRegistry.getTargetContext()

    @Before
    fun saveTestPill() {
        test_pill.save()
        Pill.savePills(context)
    }

    @After
    fun clearPillsTable() {
        Pill.clearSavedPills(context)
    }

    @Test
    fun pillFromDatabaseIntegrity() {
        val saved_pills = Pill.loadSavedPills(context)
        val pill = saved_pills.first()
        assertThat("Pill has the correct id", pill.id == pill_id)
        assertThat("Pill has the correct brand name", pill.brand_name == pill_brand_name)
        assertThat("Pill has has the correct medical name", pill.medical_name == pill_medical_name)
        assertThat("Pill has the correct image path", pill.img_path == pill_img_path)
        assertThat("Pill has the correct brand name", pill.description == pill_description)
        assertThat("Pill has a last searched date in the past", pill.date_last_searched < Date())
    }

    @Test
    fun loadSavedPills() {
        val saved_pills = Pill.loadSavedPills(context)
        val found = saved_pills.filter { pill -> pill.id == test_pill.entity.id }
        assertThat("loadSavedPills returns a list of pills that contains saved pill", found.size == 1)
    }

    @Test
    fun clearSavedPills() {
        Pill.clearSavedPills(context)
        val saved_pills = Pill.loadSavedPills(context)
        assertThat("clearSavedPills clears the pills table", saved_pills.isEmpty() == true)
    }

    @Test
    fun savePills() {
        val num_new_pills = 5
        val pills = Array<Pill>(num_new_pills, { id -> Pill(id, "", "", "", "")})
        val old_pills = Pill.loadSavedPills(context)
        Pill.savePills(pills, context)
        val new_pills = Pill.loadSavedPills(context)

        assertThat("savePills add new pills to the database", old_pills.size + num_new_pills == new_pills.size)

        for( pill in pills )
            assertThat("pills should be updated to show that they have been saved", pill.saved == true)
    }

    @Test
    fun savingPillTwice() {
        val old_pill = Pill.loadSavedPills(context).first()
        val pill = Pill(pill_id, pill_brand_name, pill_medical_name, pill_img_path, pill_description)
        pill.save()
        Pill.savePills(context)
        val new_pill = Pill.loadSavedPills(context).first()
        assertThat("new pill should overwrite old pill data", old_pill.date_last_searched != new_pill.date_last_searched)
    }

}