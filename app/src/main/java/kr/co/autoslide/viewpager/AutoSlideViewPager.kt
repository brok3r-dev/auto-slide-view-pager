package kr.co.autoslide.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import java.util.*

class AutoSlideViewPager(context: Context) : FrameLayout(context), Runnable, View.OnTouchListener {
    companion object {
        const val SLIDE_INTERVAL = 3000L
    }

    private val mHandler = Handler(Looper.getMainLooper())
    private lateinit var mViewPager: ViewPager
    private var items: List<String>? = null
    private var isSlideEnabled = true
    private var direction = 1

    @SuppressLint("ClickableViewAccessibility")
    private fun create() {
        items?.let { items ->
            mViewPager = ViewPager(context)
            val adapter = ViewPagerAdapter(context, items)

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
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let { e ->
            when (e.action) {
                MotionEvent.ACTION_UP -> {
                    isSlideEnabled = true
                    mHandler.postDelayed(this, SLIDE_INTERVAL)
                }
                else -> {
                    isSlideEnabled = false
                    mHandler.removeCallbacks(this)
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

    constructor(context: Context, items: List<String>) : this(context) {
        this.items = items
        create()
    }
}