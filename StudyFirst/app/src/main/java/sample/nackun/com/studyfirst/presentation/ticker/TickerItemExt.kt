package sample.nackun.com.studyfirst.presentation.ticker

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImg")
fun ImageView.setImg(src: Int) {
    this.setImageResource(src)
}