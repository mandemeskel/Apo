package io.mta.apo

import android.graphics.ColorFilter
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.ImageButton
import org.hamcrest.Description
import org.hamcrest.Matcher


/**
 * Created by Michael T. Andemeskel on 12/2/17.
 */
fun withColorFilter(color: ColorFilter): Matcher<View> {
    return object: BoundedMatcher<View, ImageButton>(ImageButton::class.java) {
        public override fun matchesSafely(img: ImageButton?): Boolean {
            return color == img!!.colorFilter
        }

        override fun describeTo(description: Description?) {
            description!!.appendText("with color filter: " + color.toString())
        }
    }
}