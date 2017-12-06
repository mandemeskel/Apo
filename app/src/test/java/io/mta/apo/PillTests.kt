package io.mta.apo

import org.junit.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

/**
 * Created by Michael T. Andemeskel on 12/5/17.
 */
class PillTests {
    val test_pill = Pill("Advil", "medical name", "img/path/img.jpg", "Wonder drug.")
    @BeforeAll
    fun save_test_pill() {
        test_pill.save()
    }

    @AfterAll
    fun clear_saved_pills() {
        Pill.clearSavedPills()
    }

    @Test
    fun pill_can_be_saved_and_loaded() {
        val saved_pills = Pill.loadSavedPills()
        assert(test_pill in saved_pills)
    }

    @Test
    fun saved_pills_can_be_cleared() {
        Pill.clearSavedPills()
        val saved_pills = Pill.loadSavedPills()
        assert(saved_pills.isEmpty() == true)
    }

}