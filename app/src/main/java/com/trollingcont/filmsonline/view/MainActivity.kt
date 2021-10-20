package com.trollingcont.filmsonline.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.trollingcont.filmsonline.MyApplication
import com.trollingcont.filmsonline.R
import com.trollingcont.filmsonline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openMainListFragment()
        }
    }

    private fun openMainListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainListFragment.newInstance())
            .commit()
    }
}