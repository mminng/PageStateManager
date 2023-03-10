package com.simple.pagestatemanager.lottie

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.github.mminng.pagestate.PageStateManager
import com.simple.pagestatemanager.R

class LottieActivity : AppCompatActivity() {

    private lateinit var pageManager: PageStateManager
    private var _loaded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
        val actionBar = supportActionBar
        actionBar?.let {
            actionBar.title = "Lottie"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        pageManager = PageStateManager.Builder(this)
            .setPageChangeListener {
                pageLoadingChanged { visible, view ->
                    val lottie: LottieAnimationView = view.findViewById(R.id.lottie_loading_view)
                    if (visible) {
                        lottie.playAnimation()
                    } else {
                        lottie.cancelAnimation()
                    }
                }
                pageEmptyChanged { visible, view ->
                    val lottie: LottieAnimationView = view.findViewById(R.id.lottie_empty_view)
                    if (visible) {
                        lottie.playAnimation()
                    } else {
                        lottie.cancelAnimation()
                    }
                }
                pageErrorChanged { visible, view ->
                    val lottie: LottieAnimationView = view.findViewById(R.id.lottie_error_view)
                    if (visible) {
                        lottie.playAnimation()
                    } else {
                        lottie.cancelAnimation()
                    }
                }
            }.setLoadingLayout(R.layout.lottie_state_loading)
            .setEmptyLayout(
                layoutId = R.layout.lottie_state_empty,
                clickId = R.id.lottie_empty_btn
            )
            .setErrorLayout(
                layoutId = R.layout.lottie_state_error,
                clickId = R.id.lottie_error_btn
            ).build()
        pageManager.setReloadListener {
            load((1..3).random())
        }
        load()
    }

    private fun load(showState: Int = 3) {
        pageManager.showLoading()
        Handler().postDelayed({
            when (showState) {
                1 -> {
                    pageManager.showContent()
                    _loaded = true
                }
                2 -> pageManager.showEmpty()
                3 -> pageManager.showError()
            }
        }, 3000)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loading -> {
                showToast()
                pageManager.showLoading()
            }
            R.id.content -> {
                showToast()
                pageManager.showContent()
                _loaded = true
            }
            R.id.empty -> {
                showToast()
                pageManager.showEmpty()
            }
            R.id.error -> {
                showToast()
                pageManager.showError()
            }
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_lottie_page_state, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showToast() {
        if (_loaded) {
            Toast.makeText(this, "Content loaded", Toast.LENGTH_SHORT).show()
        }
    }
}