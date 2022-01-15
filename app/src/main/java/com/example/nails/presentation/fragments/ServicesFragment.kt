package com.example.nails.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nails.*
import com.example.nails.presentation.adapter.ServiceAdapter
import com.example.nails.databinding.FragmentServicesBinding
import com.example.nails.domain.model.Service
import com.example.nails.data.network.NetworkService
import com.example.nails.presentation.MainActivity
import com.example.nails.presentation.ScreenState
import com.example.nails.presentation.onClickFlow
import com.example.nails.presentation.onRefreshFlow
import com.example.nails.presentation.viewmodel.ServicesViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi

class ServicesFragment : Fragment(R.layout.fragment_services){
    private lateinit var binding: FragmentServicesBinding

    companion object{
        fun newInstance() = ServicesFragment()
    }
    private val brandsViewModel by lazy { ServicesViewModel(requireContext(), lifecycleScope) }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rvServices.isVisible
        RefreshServices.isRefreshing = isLoading && rvServices.isVisible
    }

    private fun setData(services: List<Service>?) = with(binding){
        rvServices.isVisible = services != null
        binding.rvServices.layoutManager = LinearLayoutManager(context)
        binding.rvServices.adapter =
            ServiceAdapter(services ?: listOf()){
                (activity as MainActivity).navigateToFragment(
                    MastersFragment.newInstance())
            }
        binding.icProfile.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(AuthorizationFragment.newInstance())
        }
    }

    private fun setError(message: String?) = with(binding){
        ErrLayout.isVisible = message != null
        textError.text = message
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentServicesBinding.bind(view)
        merge(
            flowOf(Unit),
            binding.RefreshServices.onRefreshFlow(),
            binding.buttonError.onClickFlow()
        )
            .flatMapLatest {loadServices()}
            .distinctUntilChanged()
            brandsViewModel.screenState
            .onEach{
                when(it){
                    is ScreenState.DataLoaded -> {
                        setLoading(false)
                        setError(null)
                        setData(it.services)
                    }
                    is ScreenState.Error -> {
                        setLoading(false)
                        setError(it.error)
                        setData(null)
                    }
                    ScreenState.Loading -> {
                        setLoading(true)
                        setError(null)
                    }
                }
            }
            .launchIn(lifecycleScope)

        if(savedInstanceState == null){
            brandsViewModel.loadData()
        }
        binding.RefreshServices.setOnRefreshListener {
            brandsViewModel.loadData()
        }
        binding.RefreshServices.setOnRefreshListener {
            brandsViewModel.loadData()
        }

    }
    @ExperimentalSerializationApi
    private fun loadServices() = flow {
        emit(ScreenState.Loading)
        val services = NetworkService.loadServices()
        emit(ScreenState.DataLoaded(services))
    }
        .catch{
            emit(ScreenState.Error(getString(R.string.error)))
        }
}
