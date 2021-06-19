package com.example.tacos.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tacos.models.Product

class SharedProductViewModel : ViewModel() {
    val selectedProduct = MutableLiveData<Product>()

    fun select(product : Product) {
        selectedProduct.value = product
    }
}