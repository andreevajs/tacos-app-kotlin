package com.example.tacos.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.tacos.databinding.FragmentProductDetailsBinding
import com.example.tacos.models.Product
import com.example.tacos.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var _binding : FragmentProductDetailsBinding
    private val _viewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.selectedProduct.observe(viewLifecycleOwner) { product ->
            Log.d("Product details", product.toString())
            _binding.productDetailsName.text = product.name
            _binding.productDetailsRecipe.text = product.recipe
        }

        _binding.btnShareProduct.setOnClickListener {
            shareProduct(_viewModel.selectedProduct.value)
        }
    }

    private fun shareProduct(product : Product?)
    {
        val share = Intent(Intent.ACTION_SEND)
        
        val contents = String.format(
                "Look at this delicious thing - ${product?.name}!\n\n" +
                "Recipe:\n ${product?.recipe}\n" +
                "I found it here: (${product?.url})")

        share
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TITLE, product?.name)
            .putExtra(Intent.EXTRA_TEXT, contents)

        startActivity(Intent.createChooser(share, "Share recipe!"))
    }
}