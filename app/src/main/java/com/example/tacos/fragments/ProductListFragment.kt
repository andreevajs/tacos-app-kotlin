package com.example.tacos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tacos.R
import com.example.tacos.adapters.ProductItemAdapter
import com.example.tacos.databinding.FragmentProductListBinding
import com.example.tacos.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var _binding :FragmentProductListBinding
    private val _viewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ViewModel", _viewModel.toString())

        showLoadingAnimation()

        createItems()
    }

    private fun showLoadingAnimation() {
        Glide.with(this)
            .load(R.raw.happytaco)
            .into(_binding.imageviewLoading)
    }

    private fun createItems() {
        val adapter = ProductItemAdapter { item ->
            _viewModel.selectProduct(item.product)
            findNavController().navigate(R.id.action_productsFragment_to_productDetailsFragment)
        }

        val recyclerView: RecyclerView = _binding.recyclerViewProducts
        recyclerView.adapter = adapter

        _viewModel.items.observe(viewLifecycleOwner,{ products ->
            adapter.setItems(products)
            _binding.imageviewLoading.visibility = View.INVISIBLE
        })
    }
}