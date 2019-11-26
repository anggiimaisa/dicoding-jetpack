package com.example.themovieapp.ui.detail

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.runner.RunWith
import com.example.themovieapp.DetailMovieActivity
import android.content.Intent
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import com.example.themovieapp.utils.DataDummy
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.orchestrator.junit.BundleJUnitUtils.getDescription
import com.example.themovieapp.R
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class DetailMovieActivityTest {

    private val dataDummyMovie = DataDummy.getMovieDatas().get(0)
    private val dataDummyTVShow = DataDummy.getTVShowDatas().get(0)

    @Rule
    @JvmField var detailMovieActivityActivityTestRule: ActivityTestRule<DetailMovieActivity> =
        object : ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext()
                val result = Intent(targetContext, DetailMovieActivity::class.java)
                result.putExtra(DetailMovieActivity.EXTRA_ID, dataDummyMovie.movieId)
                return result
            }
        }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_title)).check(matches(withText(dataDummyMovie.movieTitle)))
        onView(withId(R.id.movie_description)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_description)).check(matches(withText(dataDummyMovie.movieDescription)))
        onView(withId(R.id.movie_image)).check(matches(isDisplayed()))
    }

}