package com.autoslide

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager

class AutoSlideFrameLayout(context: Context) : FrameLayout(context), Runnable, View.OnTouchListener {
    companion object {
        const val SLIDE_INTERVAL = 3000L
    }

    private val mHandler = Handler(Looper.getMainLooper())
    private lateinit var mViewPager: ViewPager
    private var isSlideEnabled = true
    private var direction = 1

    @SuppressLint("ClickableViewAccessibility")
    private fun create() {
        mViewPager = ViewPager(context)
        val adapter = ViewPagerAdapter(context)

        mViewPager.apply {
            this.overScrollMode = OVER_SCROLL_IF_CONTENT_SCROLLS
            this.id = ViewCompat.generateViewId()
            this.adapter = adapter
            this.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }

        mViewPager.setOnTouchListener(this)

        this.addView(mViewPager)

        mHandler.postDelayed(this, SLIDE_INTERVAL)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let { e ->
            when (e.action) {
                MotionEvent.ACTION_UP -> {
                    isSlideEnabled = true
                    handler.postDelayed(this, SLIDE_INTERVAL)
                }
                else -> {
                    isSlideEnabled = false
                    handler.removeCallbacks(this)
                }
            }
        }

        return false
    }

    override fun run() {
        var next = mViewPager.currentItem + direction
        val size = mViewPager.adapter?.count ?: return

        if (next >= size) {
            direction = -1
            next -= 2
        } else if (next < 0) {
            direction = 1
            next += 2
        }

        mViewPager.setCurrentItem(next, true)
        if (isSlideEnabled) {
            mHandler.postDelayed(this, SLIDE_INTERVAL)
        }
    }

    init { create() }
}