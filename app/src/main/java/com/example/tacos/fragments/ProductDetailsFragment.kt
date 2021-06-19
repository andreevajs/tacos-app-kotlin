package com.example.tacos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tacos.databinding.FragmentProductDetailsBinding
import com.example.tacos.viewmodels.ProductsViewModel
import com.example.tacos.viewmodels.SharedProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var _binding : FragmentProductDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SharedProductViewModel::class.java)

        viewModel.selectedProduct.observe(viewLifecycleOwner,{product ->
            Log.d("Product details",product.toString())
            _binding.productDetailsName.text = product.name
            _binding.productDetailsRecipe.text = product.recipe
        })
    }
}