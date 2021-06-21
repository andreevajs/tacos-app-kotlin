package com.example.tacos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tacos.R
import com.example.tacos.databinding.FragmentMenuBinding
import com.example.tacos.models.ProductType
import com.example.tacos.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var _binding : FragmentMenuBinding
    private val _productsViewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ViewModel", _productsViewModel.toString())
        _binding.btnBaseLayers.setOnClickListener {
            selectProductType(ProductType.BASE_LAYER)
            findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
        }
        _binding.btnCondiments.setOnClickListener {
            selectProductType(ProductType.CONDIMENT)
            findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
        }
        _binding.btnMixins.setOnClickListener {
            selectProductType(ProductType.MIXIN)
            findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
        }
        _binding.btnSeasonings.setOnClickListener {
            selectProductType(ProductType.SEASONING)
            findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
        }
        _binding.btnShells.setOnClickListener {
            selectProductType(ProductType.SHELL)
            findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
        }

    }

    private fun selectProductType(productType: ProductType) {
        _productsViewModel.selectType(productType)
    }
}