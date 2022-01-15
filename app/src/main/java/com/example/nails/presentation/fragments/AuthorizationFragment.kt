package com.example.nails.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nails.presentation.MainActivity
import com.example.nails.R
import com.example.nails.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {
    companion object{
        fun newInstance() = AuthorizationFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAuthorizationBinding.bind(view)
        binding.SignUp.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ServicesFragment.newInstance())
        }
        binding.button.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ServicesFragment.newInstance())
        }

    }
}