package com.example.mvvmsampleappication

import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.example.mvvmsampleappication.view.activity.logitem.LogItemActivity
import com.example.mvvmsampleappication.view.activity.main.MainActivity
import org.hamcrest.Matchers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addRecordMenuIcon() {
        onView(
            Matchers.allOf(withId(R.id.add_record), withContentDescription(R.string.menu_label_add_record))
        ).check(matches(isDisplayed()))
    }

    @Test
    fun addRecordMenu() {
        // ResultActivityの起動を監視
        val monitor = Instrumentation.ActivityMonitor(
            LogItemActivity::class.java.canonicalName,
            null,
            false
        )
        getInstrumentation().addMonitor(monitor)

        // 追加メニューをクリック
        onView(
            Matchers.allOf(
                withId(R.id.add_record),
                withContentDescription("記録を追加")
            )
        ).perform(click())

        // ResultActivityが起動したか確認
        val resultActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 1000L)
        assertThat(monitor.hits).isEqualTo(1)
        assertThat(resultActivity).isNotNull

        Espresso.pressBack()
        assertThat(resultActivity.isFinishing).isTrue
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