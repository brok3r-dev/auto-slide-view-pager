package kr.co.autoslide.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kr.co.autoslide.R
import kr.co.autoslide.databinding.ViewHolderImageBinding
import java.util.*

class ImageViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(item: String) {
        itemView.apply {
            val binding = ViewHolderImageBinding.bind(this)

            Glide.with(binding.imageView)
                .load(item)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)
        }
    }

    companion object {
        fun create(parent: ViewGroup, context: Context): ImageViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.view_holder_image, parent, false)
            return ImageViewHolder(view)
        }
    }
}