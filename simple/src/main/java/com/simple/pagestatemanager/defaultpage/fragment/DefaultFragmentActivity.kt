package com.simple.pagestatemanager.defaultpage.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.simple.pagestatemanager.R

class DefaultFragmentActivity : AppCompatActivity() {

    private val fragment: DefaultFragment = DefaultFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_default)
        val actionBar = supportActionBar
        actionBar?.let {
            actionBar.title = "Default(On Fragment)"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.fragment_content, fragment)
        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loading -> {
                fragment.showLoading()
            }
            R.id.content -> {
                fragment.showContent()
            }
            R.id.empty -> {
                fragment.showEmpty()
            }
            R.id.error -> {
                fragment.showError()
            }
            R.id.empty_change -> {
                fragment.showEmpty("Okay...", R.drawable.ic_empty)
            }
            R.id.error_change -> {
                fragment.showError("Oh no!", R.drawable.ic_error)
            }
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_default_page_state, menu)
        return super.onCreateOptionsMenu(menu)
    }
}