package com.example.tacos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tacos.R
import com.example.tacos.api.TacosApiService
import com.example.tacos.databinding.FragmentMenuBinding
import com.example.tacos.databinding.FragmentProductsBinding
import com.example.tacos.models.Product
import com.example.tacos.repositories.TacosRepository
import com.example.tacos.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    @Inject lateinit var repository: TacosRepository
    @Inject lateinit var viewModel: ProductsViewModel


    private lateinit var _binding :FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(layoutInflater)
        viewModel._products.observe(viewLifecycleOwner,{ products ->
            _binding.tv.text = products.toString()
        })
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
    }
}