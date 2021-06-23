package com.example.tacos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tacos.databinding.FragmentTacosBinding
import com.example.tacos.viewmodels.RandomTacoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TacosFragment : Fragment() {

    private lateinit var _binding : FragmentTacosBinding
    private val _viewModel: RandomTacoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTacosBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.btnGetRandomTaco.setOnClickListener {
            _viewModel.randomizeTaco()
        }

        _viewModel.taco.observe(viewLifecycleOwner) { tacos ->
            _binding.tacosBaseLayer.text = tacos.baseLayer.name
            _binding.tacosMixin.text = tacos.mixin.name
            _binding.tacosCondiment.text = tacos.condiment.name
            _binding.tacosSeasoning.text = tacos.seasoning.name
            _binding.tacosShell.text = tacos.shell.name
        }

        _binding.btnSave.setOnClickListener {
            _viewModel.saveTaco()
            Snackbar.make(it, "Taco saved!", Snackbar.LENGTH_SHORT).show()
        }

        _viewModel.randomizeTaco()
    }
}