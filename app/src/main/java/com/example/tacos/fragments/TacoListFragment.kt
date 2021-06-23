package com.example.tacos.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tacos.R
import com.example.tacos.adapters.TacoAdapter
import com.example.tacos.databinding.FragmentTacoListBinding
import com.example.tacos.models.Taco
import com.example.tacos.viewmodels.ProductsViewModel
import com.example.tacos.viewmodels.TacosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TacoListFragment : Fragment() {

    private lateinit var _binding : FragmentTacoListBinding
    private val _viewModel: TacosViewModel by viewModels()
    private val _productsViewModel: ProductsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTacoListBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TacoAdapter(
            onItemClick = {clickTaco(it)},
            onItemDeleteClick = {deleteTaco(it)},
        )

        val recyclerView: RecyclerView = _binding.tacosRecyclerView
        recyclerView.adapter = adapter

        _viewModel.items.observe(viewLifecycleOwner,{ tacos ->
            adapter.setItems(tacos)
        })

        _viewModel.loadSavedTacos()
    }

    private fun clickTaco(taco : Taco) {
        _productsViewModel.setItems(taco.getProducts())
        findNavController().navigate(R.id.action_tacoListFragment_to_productsFragment)
    }

    private fun deleteTaco(taco : Taco) {
        AlertDialog.Builder(context)
            .setTitle("Delete taco?")
            .setPositiveButton("Yes") { _, _ -> _viewModel.deleteTaco(taco) }
            .setNegativeButton("No") { _, _ ->}
            .show()
    }

}