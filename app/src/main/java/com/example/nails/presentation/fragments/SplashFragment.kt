package com.example.nails.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nails.R
import com.example.nails.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {
    companion object{
        fun newInstance() = SplashFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSplashBinding.bind(view)
    }
}