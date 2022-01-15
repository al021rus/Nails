package com.example.nails.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nails.presentation.MainActivity
import com.example.nails.R
import com.example.nails.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    companion object{
        fun newInstance() = RegistrationFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
        binding.SignUp.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ServicesFragment.newInstance())
        }
    }
}