package com.example.themovieapp

import android.view.View
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.NoMatchingViewException
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import junit.framework.Assert.assertNotNull
import org.hamcrest.CoreMatchers.`is`


class RecyclerViewItemCountAssertion(expectedCount: Int) : ViewAssertion {

    private var expectedCount: Int? = null

    init {
        this.expectedCount = expectedCount
    }

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertNotNull(adapter)
        assertThat(adapter!!.itemCount, `is`(expectedCount))
    }
}