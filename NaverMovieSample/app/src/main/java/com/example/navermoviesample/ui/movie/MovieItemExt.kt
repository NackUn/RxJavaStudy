package com.example.navermoviesample.ui.movie

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setUrlImg")
fun ImageView.setUrlImg(url: String) {
    Glide.with(this).load(url).into(this)
}