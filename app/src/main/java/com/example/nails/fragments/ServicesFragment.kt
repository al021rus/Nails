package com.example.nails.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nails.MainActivity
import com.example.nails.R
import com.example.nails.adapter.ServiceAdapter
import com.example.nails.data.DataSource
import com.example.nails.databinding.FragmentServicesBinding

class ServicesFragment : Fragment(R.layout.fragment_services){
    companion object{
        fun newInstance() = ServicesFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentServicesBinding.bind(view)
        binding.rvServices.layoutManager = LinearLayoutManager(requireContext())
        binding.rvServices.adapter = ServiceAdapter(DataSource.Services) {
            (activity as MainActivity).navigateToFragment(MastersFragment.newInstance())
        }
        binding.icProfile.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(AuthorizationFragment.newInstance())
        }

    }
}
