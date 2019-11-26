package com.example.themovieapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private var mActivity: MainActivity? = null
    private val tab_movies = "Movie"
    private val tab_tv_show = "TV Show"

    @Rule
    @JvmField var activityMain = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        mActivity = activityMain.activity
    }

    @Test
    fun swipeViewPager() {
        onView(withId(R.id.view_pager))
            .check(matches(isDisplayed()))

        onView(withId(R.id.view_pager))
            .perform(swipeLeft())

        onView(withId(R.id.view_pager))
            .perform(swipeRight())
    }

    @Test
    fun tabLayoutSwitch() {
        onView(allOf(withText(tab_tv_show), isDescendantOfA(withId(R.id.tabs))))
            .perform(click())
            .check(matches(isDisplayed()))

        onView(allOf(withText(tab_movies), isDescendantOfA(withId(R.id.tabs))))
            .perform(click())
            .check(matches(isDisplayed()))
    }
}