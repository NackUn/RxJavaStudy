package com.example.navermoviesample.ui.movie

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navermoviesample.base.BaseRecyclerView
import com.example.navermoviesample.vo.MovieItem

@BindingAdapter("setItems")
fun RecyclerView.setItems(items: List<MovieItem>) {
    (this.adapter as? BaseRecyclerView.BaseAdapter<Any, *>)?.run {
        setItems(items)
        notifyDataSetChanged()
    }
}