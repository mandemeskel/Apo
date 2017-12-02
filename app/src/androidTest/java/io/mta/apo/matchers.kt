package io.mta.apo

import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.ImageButton
import org.hamcrest.Description

/**
 * Created by Michael T. Andemeskel on 12/2/17.
 */
class withFilterColor: BoundedMatcher<View, ImageButton>(ImageButton::class.java) {
    override fun matchesSafely(item: ImageButton?): Boolean {
        // TODO
    }

    override fun describeTo(description: Description?) {
        // TODO
    }
}