package com.example.tacos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
            goToProductsList(ProductType.BASE_LAYER)
        }
        _binding.btnCondiments.setOnClickListener {
            goToProductsList(ProductType.CONDIMENT)
        }
        _binding.btnMixins.setOnClickListener {
            goToProductsList(ProductType.MIXIN)
        }
        _binding.btnSeasonings.setOnClickListener {
            goToProductsList(ProductType.SEASONING)
        }
        _binding.btnShells.setOnClickListener {
            goToProductsList(ProductType.SHELL)
        }
        _binding.btnTaco.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_tacosFragment)
        }
        _binding.btnMyTacos.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_tacoListFragment)
        }

    }

    private fun goToProductsList(productType: ProductType) {
        _productsViewModel.selectType(productType)
        _productsViewModel.load()
        findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
    }
}