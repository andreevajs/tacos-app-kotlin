package com.example.tacos.repositories

import com.example.tacos.api.TacosApiService
import com.example.tacos.models.Product
import com.example.tacos.models.Taco
import javax.inject.Inject

class TacosRepository @Inject constructor(
    private val api : TacosApiService
) {
    suspend fun getBaseLayers(): List<Product>? {
        val response = api.getBaseLayers()
        return response.body()
    }

    suspend fun getMixins(): List<Product>? {
        val response = api.getMixins()
        return response.body()
    }

    suspend fun getCondiments(): List<Product>? {
        val response = api.getCondiments()
        return response.body()
    }

    suspend fun getSeasonings(): List<Product>? {
        val response = api.getSeasonings()
        return response.body()
    }

    suspend fun getShells(): List<Product>? {
        val response = api.getShells()
        return response.body()
    }

    suspend fun getRandomTaco(): Taco? {
        val response = api.getRandomTaco()
        return response.body()
    }
}