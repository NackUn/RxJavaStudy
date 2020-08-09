package com.example.navermoviesample.ui.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.navermoviesample.BR
import com.example.navermoviesample.R
import com.example.navermoviesample.base.BaseFragment
import com.example.navermoviesample.base.BaseRecyclerView
import com.example.navermoviesample.databinding.MovieFragmentBinding
import com.example.navermoviesample.databinding.MovieItemBinding
import com.example.navermoviesample.vo.MovieItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_fragment.*

@AndroidEntryPoint
class MovieFragment : BaseFragment<MovieFragmentBinding, MovieViewModel>(
    R.layout.movie_fragment
) {
    private val movieAdapter =
        object : BaseRecyclerView.BaseAdapter<List<MovieItem>, MovieItemBinding>(
            R.layout.movie_item,
            BR.movieItem
        ) {}

    override val vm by viewModels<MovieViewModel>()

    fun initViewModel() {
        binding.setVariable(BR.vm, vm)
    }

    @SuppressLint("CheckResult")
    private fun setOnItemTouchListener() {
        movieAdapter.getOnItemClickObservable().subscribe {
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(vm.movieItems.value!![it].link))
            startActivity(webIntent)
        }
    }

    private fun setAdapter() {
        movieRecyclerView.adapter = movieAdapter
    }

    private fun setErrorMessageObserver() {
        vm.errorMessage.observe(viewLifecycleOwner, Observer {
            it.let {
                showToast(it)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        setAdapter()
        setOnItemTouchListener()
        setErrorMessageObserver()
    }
}