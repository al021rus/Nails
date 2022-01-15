package com.example.nails.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nails.MainActivity
import com.example.nails.R
import com.example.nails.adapter.ServiceAdapter
import com.example.nails.databinding.FragmentServicesBinding
import com.example.nails.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class ServicesFragment : Fragment(R.layout.fragment_services){
    private lateinit var binding: FragmentServicesBinding
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ context,exception ->
        exception.printStackTrace()
        binding.progressBar.visibility = GONE
        binding.rvServices.adapter =
            ServiceAdapter(listOf()) {}
        binding.RefreshServices.isRefreshing = false
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
        fun newInstance() = ServicesFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentServicesBinding.bind(view)
        binding.icProfile.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(AuthorizationFragment.newInstance())
        }

        loadServices()

        binding.RefreshServices.setOnRefreshListener {
            binding.RefreshServices.isRefreshing = true
            loadServices()
            binding.RefreshServices.isRefreshing = false
        }
    }
    @ExperimentalSerializationApi
    private fun loadServices() {
        scope.launch {
            val services = NetworkService.loadServices()
            binding.rvServices.layoutManager = LinearLayoutManager(context)
            binding.rvServices.adapter =
                ServiceAdapter(services) {
                    (activity as MainActivity).navigateToFragment(
                        MastersFragment.newInstance())
                }
            binding.progressBar.visibility = GONE
            binding.RefreshServices.isRefreshing = false
        }
    }
}
