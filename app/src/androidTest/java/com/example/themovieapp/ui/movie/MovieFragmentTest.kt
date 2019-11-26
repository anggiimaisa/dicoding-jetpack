package com.example.themovieapp.ui.movie

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.themovieapp.*
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class MovieFragmentTest {

    @Rule
    @JvmField var singleFragmentActivity = ActivityTestRule(SingleFragmentActivity::class.java)

    @Test
    fun loadMovies() {
        singleFragmentActivity.activity.setFragment(MovieFragment.getInstance(0, 0))
        onView(withId(R.id.movies_list)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_list)).check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun loadTVShow() {
        singleFragmentActivity.activity.setFragment(MovieFragment.getInstance(1, 0))
        onView(withId(R.id.movies_list)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_list)).check(RecyclerViewItemCountAssertion(10))
    }

}