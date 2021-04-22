package kr.co.autoslide.viewpager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import java.util.*

class ViewPagerAdapter(
    private val context: Context,
    private val items: List<String>
) : PagerAdapter() {
    override fun getCount(): Int = items.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = ImageViewHolder.create(container, context)
        view.bind(items[position])
        container.addView(view.itemView)
        return view.itemView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view == `object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }
}