package com.example.nails.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nails.MainActivity
import com.example.nails.R
import com.example.nails.adapter.ReviewAdapter
import com.example.nails.databinding.FragmentReviewsBinding
import com.example.nails.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class ReviewsFragment : Fragment(R.layout.fragment_reviews) {
    private lateinit var binding: FragmentReviewsBinding
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ context,exception ->
        exception.printStackTrace()
        binding.progressBar.visibility = GONE
        binding.rvReview.adapter =
            ReviewAdapter(listOf()) {}
        binding.RefreshReviews.isRefreshing = false
        Snackbar.make(
            requireView(),
            getString(R.string.error),
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.parseColor("#ED4337"))
            .setActionTextColor(Color.parseColor("#FFFFFF"))
            .show()
    }
    private val scope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler)
    companion object{
        fun newInstance() = ReviewsFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReviewsBinding.bind(view)
        binding.icProfile.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(MastersFragment.newInstance())
        }

        loadReviews()

        binding.RefreshReviews.setOnRefreshListener {
            binding.RefreshReviews.isRefreshing = true
            loadReviews()
            binding.RefreshReviews.isRefreshing = false
        }
    }
    @ExperimentalSerializationApi
    private fun loadReviews() {
        scope.launch {
            val reviews = NetworkService.loadReviews()
            binding.rvReview.layoutManager = LinearLayoutManager(context)
            binding.rvReview.adapter =
                ReviewAdapter(reviews) {}
            binding.progressBar.visibility = GONE
            binding.RefreshReviews.isRefreshing = false
        }
    }
}