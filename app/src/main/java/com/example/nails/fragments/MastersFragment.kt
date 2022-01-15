package com.example.nails.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nails.MainActivity
import com.example.nails.R
import com.example.nails.adapter.MasterAdapter
import com.example.nails.data.DataSource.Masters
import com.example.nails.databinding.FragmentMastersBinding

class MastersFragment : Fragment(R.layout.fragment_masters) {
    companion object{
        fun newInstance() = MastersFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMastersBinding.bind(view)
        binding.rvMasters.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMasters.adapter = MasterAdapter(Masters) {
            (activity as MainActivity).navigateToFragment(ReviewsFragment.newInstance())
        }
        binding.icProfile.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ServicesFragment.newInstance())
        }

    }
}