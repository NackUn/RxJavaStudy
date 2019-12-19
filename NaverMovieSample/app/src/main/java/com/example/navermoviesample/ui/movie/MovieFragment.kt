package com.example.navermoviesample.ui.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.example.navermoviesample.BR
import com.example.navermoviesample.R
import com.example.navermoviesample.base.BaseFragment
import com.example.navermoviesample.base.BaseRecyclerView
import com.example.navermoviesample.databinding.MovieFragmentBinding
import com.example.navermoviesample.databinding.MovieItemBinding
import com.example.navermoviesample.vo.MovieItem
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : BaseFragment<MovieFragmentBinding, MovieViewModel>(
    R.layout.movie_fragment
) {
    private val movieAdapter =
        object : BaseRecyclerView.BaseAdapter<List<MovieItem>, MovieItemBinding>(
            R.layout.movie_item,
            BR.movieItem
        ){}

    override val vm: MovieViewModel = MovieViewModel()

    fun initViewModel(){
        binding.setVariable(BR.vm, vm)
    }

    private fun setOnItemTouchListener(){
        movieRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            //todo 터치가 10초동안 1번 일때
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = rv.findChildViewUnder(e.x, e.y)
                val position = rv.getChildAdapterPosition(child!!)

                val webIntent: Intent = Uri.parse(vm.movieItems.value!![position].link).let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(webIntent)
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
        })
    }

    private fun setAdapter() {
        movieRecyclerView.adapter = movieAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        setAdapter()
        setOnItemTouchListener()
    }
}