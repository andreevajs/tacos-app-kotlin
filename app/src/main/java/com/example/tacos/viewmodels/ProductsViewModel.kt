package com.example.tacos.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tacos.api.TacosApiService
import com.example.tacos.models.Product
import com.example.tacos.repositories.TacosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
        private val repository : TacosRepository
) : ViewModel()  {
    val _products = MutableLiveData<List<Product>>()
    fun load() {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("TEST_API", "sending")
             val response = repository.getBaseLayers()

            withContext(Dispatchers.Main) {
                _products.value = response
                Log.d("TEST_RESP", _products.value.toString())
            }
        }
    }
}