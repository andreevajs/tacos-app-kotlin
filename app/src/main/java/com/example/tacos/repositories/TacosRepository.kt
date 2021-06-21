package com.example.tacos.repositories

import com.example.tacos.api.TacosApiService
import com.example.tacos.models.Product
import com.example.tacos.models.ProductType
import com.example.tacos.models.Taco
import retrofit2.Response
import javax.inject.Inject

class TacosRepository @Inject constructor(
    private val api : TacosApiService
) {
    suspend fun getProducts(productType: ProductType): List<Product>? {
        val response = when (productType) {
            ProductType.BASE_LAYER -> api.getBaseLayers()
            ProductType.CONDIMENT -> api.getCondiments()
            ProductType.MIXIN -> api.getMixins()
            ProductType.SEASONING -> api.getSeasonings()
            ProductType.SHELL -> api.getShells()
        }
        return response.body()
    }

    suspend fun getRandomTaco(): Taco? {
        val response = api.getRandomTaco()
        return response.body()
    }
}