package kr.co.autoslide

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kr.co.autoslide.databinding.ActivityMainBinding
import kr.co.autoslide.viewpager.AutoSlideViewPager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = mutableListOf(
            "https://mblogthumb-phinf.pstatic.net/MjAxOTExMTZfMjc5/MDAxNTczODMyODM4MjIw.NJNYsmbwc1XHuMZsIlp2TBV13KzFd6eYAJJiVaKYIqog.xkYwdvRz8gbvfAeLDrhgF9qsraumiEQx-7Mcvtg_rgYg.JPEG.eett7777/IMG_9356.jpg?type=w800",
            "https://images.chosun.com/resizer/Whz_o9v6ZeqhQ1I4O_-O8TLmhOg=/600x400/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/5TG4HJTBM7XYH2CVC2TDKV44TY.jpg",
            "https://data.ygosu.com/upload_files/board_yeobgi/1754931/5f5de179271b9.jpg"
        )

        val slide = AutoSlideViewPager(this, items)
        binding.cardView.addView(slide)

        binding.button.setOnClickListener {
            finish()
        }
    }
}