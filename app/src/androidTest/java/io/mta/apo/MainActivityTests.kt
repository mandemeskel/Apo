package io.mta.apo

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule


/**
 * Created by Michael T. Andemeskel on 11/9/17.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @Rule
    @JvmField
    val act = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun btnRecentSearches() {
        // perform a click event
        onView(
                ViewMatchers.withId(R.id.btnRecentSearches)
        ).perform(ViewActions.click())

        // check that the page changed
        onView(
                ViewMatchers.withId(R.id.camera)
        ).check(ViewAssertions.doesNotExist())

        // check we are in recent searches page
        onView(
                ViewMatchers.withText("recent searches")
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnSearchForm() {
        // perform a click event
        onView(
                ViewMatchers.withId(R.id.btnSearchForm)
        ).perform(ViewActions.click())

        // check that the page changed
        onView(
                ViewMatchers.withId(R.id.camera)
        ).check(ViewAssertions.doesNotExist())

        // check we are in recent searches page
        onView(
                ViewMatchers.withText("search form page")
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnCapture() {
        // perform a click event
        onView(
                ViewMatchers.withId(R.id.btnCapture)
        ).perform(ViewActions.click())

        // check that the page has not changed i.e. cameraview is still there
        onView(
                ViewMatchers.withId(R.id.camera)
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // check that a picture has been taken
        // TODO: how to test this without going into the innards of the code?

    }

}