package com.example.tacos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tacos.R
import com.example.tacos.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var _binding : FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater)

        _binding.btnProducts.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
        }

        return _binding.root
    }
}