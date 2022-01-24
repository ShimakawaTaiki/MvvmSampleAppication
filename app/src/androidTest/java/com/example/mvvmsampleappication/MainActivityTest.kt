package com.example.mvvmsampleappication

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.mvvmsampleappication.view.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun inputDialogFragmentShown() {
        onView(withText(R.string.label_title)).check(matches(isDisplayed()))
        onView(withText(R.string.label_step)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_step)).check(matches(isDisplayed()))
        onView(withText(R.string.resist)).check(matches(isDisplayed()))
        onView(withText(android.R.string.cancel)).check(matches(isDisplayed()))
    }

    @Test
    fun inputStep() {
        onView(withId(R.id.edit_step)).perform(replaceText("12345"))
        onView(withText(R.string.resist)).perform(click())
        onView(withText("12345")).check(matches(isDisplayed()))

        onView(withText(R.string.label_title)).check(doesNotExist())
        onView(withId(R.id.log_list)).check(matches(withText("12345")))
    }

    @Test
    fun addRecordMenuIcon() {
        Espresso.pressBack()

        onView(
            Matchers.allOf(withId(R.id.add_record), withContentDescription(R.string.menu_label_add_record))
        ).check(matches(isDisplayed()))
    }

    @Test
    fun addRecordMenu() {
        Espresso.pressBack()

        onView(
            Matchers.allOf(withId(R.id.add_record), withContentDescription(R.string.menu_label_add_record))
        ).perform(click())

        onView(withText(R.string.label_title)).check(matches(isDisplayed()))
    }

//    @Test
//    fun addRecordList() {
//        Espresso.pressBack()
//
//        val mainActivity = activityRule.activity
//
//        mainActivity.viewModel.addStepCount(12345)
//        mainActivity.viewModel.addStepCount(666)
//
//        // リストの表示確認
//        var index = 0
//        onView(withId(R.id.log_list))
//            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
//            .check(
//                matches(
//                    atPositionOnView(
//
//                    )
//                )
//            )
//    }
}