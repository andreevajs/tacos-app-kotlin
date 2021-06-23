package com.example.tacos.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tacos.models.Product
import com.example.tacos.models.ProductItem
import com.example.tacos.models.ProductType
import com.example.tacos.repositories.TacosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
        private val repository: TacosRepository
) : ViewModel() {

    val productType = MutableLiveData<ProductType>()
    val selectedProduct = MutableLiveData<Product>()
    val items = MutableLiveData<List<ProductItem>>()

    fun load() {
        GlobalScope.launch(Dispatchers.IO) {

            val response = repository.getProducts(productType.value!!)

            withContext(Dispatchers.Main) {
                items.value = response?.map {ProductItem(it)}
                Log.d("API", "items recieved: "+ items.value?.size.toString())
            }
        }
    }

    fun selectType(type : ProductType) {
        productType.value = type
    }

    fun selectProduct(product : Product) {
        selectedProduct.value = product
    }

    fun setItems(products: List<Product>) {
        items.value = products.map {ProductItem(it)}
    }
}