package com.example.nails.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nails.presentation.MainActivity
import com.example.nails.R
import com.example.nails.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    companion object{
        fun newInstance() = ProfileFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
        binding.button.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ServicesFragment.newInstance())
        }

    }
}