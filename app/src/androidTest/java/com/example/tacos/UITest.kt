package com.example.tacos


import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun showLoading() {
        onView(withId(R.id.btn_mixins)).perform(click())
        onView(withId(R.id.imageview_loading)).check(matches(isDisplayed()))
    }

    @Test
    fun saveTaco() {
        onView(withId(R.id.btn_taco)).perform(click())
        onView(withId(R.id.btn_get_random_taco)).perform(click())
        SystemClock.sleep(3000)
        onView(withId(R.id.btn_save)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.btn_my_tacos)).perform(click())
        SystemClock.sleep(1000)
        onView(withId(R.id.tacos_base_layer)).check(matches(isDisplayed()))
    }
}
