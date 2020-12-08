package com.example.surfedmemes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.surfedmemes.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var memesFragment: MemesFragment
    private lateinit var newMemeFragment: NewMemeFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setSupportActionBar(toolbar)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_show_memes ->
                    showMemes()

                R.id.action_add_mem ->
                    addMeme()

                R.id.action_open_profile ->
                    openProfile()

                else -> false
            }
        }
    }

    private fun init() {
        toolbar = findViewById(R.id.tb_main)
        bottomNavigationView = findViewById(R.id.bnv_main_screen)
        memesFragment = MemesFragment()
        newMemeFragment = NewMemeFragment()
        profileFragment = ProfileFragment()
    }

    private fun showMemes(): Boolean {
        toolbar.title = getText(R.string.memes)
        supportFragmentManager.beginTransaction().replace(R.id.fl_main_screen, memesFragment)
            .commit()
        return true
    }

    private fun addMeme(): Boolean {
        toolbar.title = ""
        supportFragmentManager.beginTransaction().replace(R.id.fl_main_screen, newMemeFragment)
            .commit()
        return true
    }

    private fun openProfile(): Boolean {
        toolbar.title = ""
        supportFragmentManager.beginTransaction().replace(R.id.fl_main_screen, profileFragment)
            .commit()
        return true
    }
}