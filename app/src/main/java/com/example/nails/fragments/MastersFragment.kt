package com.example.nails.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nails.MainActivity
import com.example.nails.R
import com.example.nails.adapter.MasterAdapter
import com.example.nails.databinding.FragmentMastersBinding
import com.example.nails.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class MastersFragment : Fragment(R.layout.fragment_masters) {
    private lateinit var binding: FragmentMastersBinding
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ context,exception ->
        exception.printStackTrace()
        binding.progressBar.visibility = GONE
        binding.rvMasters.adapter =
            MasterAdapter(listOf()) {}
        binding.RefreshMasters.isRefreshing = false
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
        fun newInstance() = MastersFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMastersBinding.bind(view)
        binding.icProfile.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ServicesFragment.newInstance())
        }

        loadMasters()

        binding.RefreshMasters.setOnRefreshListener {
            binding.RefreshMasters.isRefreshing = true
            loadMasters()
            binding.RefreshMasters.isRefreshing = false
        }
    }
    @ExperimentalSerializationApi
    private fun loadMasters() {
        scope.launch {
            val masters = NetworkService.loadMasters()
            binding.rvMasters.layoutManager = LinearLayoutManager(context)
            binding.rvMasters.adapter =
                MasterAdapter(masters) {
                    (activity as MainActivity).navigateToFragment(
                        ReviewsFragment.newInstance())
                }
            binding.progressBar.visibility = GONE
            binding.RefreshMasters.isRefreshing = false
        }
    }
}