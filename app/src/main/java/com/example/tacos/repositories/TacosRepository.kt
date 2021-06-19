package com.example.tacos.repositories

import com.example.tacos.api.TacosApiService
import com.example.tacos.models.Product
import javax.inject.Inject

class TacosRepository @Inject constructor(
    private val api : TacosApiService
) {
    suspend fun getBaseLayers(): List<Product>? {
        val response = api.getMixins()
        return response.body()
    }
}