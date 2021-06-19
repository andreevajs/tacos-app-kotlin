package com.example.tacos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tacos.R
import com.example.tacos.adapters.ProductItemAdapter
import com.example.tacos.databinding.FragmentProductsBinding
import com.example.tacos.repositories.TacosRepository
import com.example.tacos.viewmodels.ProductsViewModel
import com.example.tacos.viewmodels.SharedProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    @Inject lateinit var repository: TacosRepository
    private lateinit var _binding :FragmentProductsBinding
    private val _viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(R.raw.happytaco)
            .into(_binding.imageviewLoading)

        val adapter = ProductItemAdapter { item ->
            val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedProductViewModel::class.java)
            sharedViewModel.select(item.product)
            findNavController().navigate(R.id.action_productsFragment_to_productDetailsFragment)
        }

        val recyclerView: RecyclerView = _binding.recyclerViewProducts
        recyclerView.adapter = adapter

        _viewModel.items.observe(viewLifecycleOwner,{ products ->
            adapter.setItems(products)
            _binding.imageviewLoading.visibility = View.INVISIBLE
        })
        _viewModel.load()
    }
}