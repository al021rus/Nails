package com.example.nails.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nails.MainActivity
import com.example.nails.R
import com.example.nails.adapter.ReviewAdapter
import com.example.nails.data.DataSource
import com.example.nails.databinding.FragmentReviewsBinding

class ReviewsFragment : Fragment(R.layout.fragment_reviews) {
    companion object{
        fun newInstance() = ReviewsFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReviewsBinding.bind(view)
        binding.rvReview.layoutManager = LinearLayoutManager(requireContext())
        binding.rvReview.adapter = ReviewAdapter(DataSource.Reviews) {}
        binding.icProfile.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(MastersFragment.newInstance())
        }

    }
}