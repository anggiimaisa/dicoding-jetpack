package com.example.themovieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.themovieapp.databinding.ActivityFavoriteBinding
import com.example.themovieapp.databinding.ActivityMainBinding
import com.example.themovieapp.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.view.*

class FavoriteActivity : AppCompatActivity() {

    lateinit var binding : ActivityFavoriteBinding

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, FavoriteActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, 1)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}