package com.example.tacos.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.example.tacos.api.TacosApiService
import com.example.tacos.data.db.AppDatabase
import com.example.tacos.models.Product
import com.example.tacos.models.ProductType
import com.example.tacos.models.Taco
import javax.inject.Inject


class TacosRepository @Inject constructor(
    private val api: TacosApiService,
    private val db: AppDatabase,
    private val connectivityManager: ConnectivityManager
) {
    suspend fun getProducts(productType: ProductType): List<Product>? {

        if (isOnline()) {
            val products = loadFromApi(productType)
            if (products != null) {
                saveAll(products, productType)
            }
            return products
        } else {
            return loadFromDatabase(productType)
        }
    }

    private suspend fun loadFromApi(productType: ProductType) : List<Product>? {
        val response = when (productType) {
            ProductType.BASE_LAYER -> api.getBaseLayers()
            ProductType.CONDIMENT -> api.getCondiments()
            ProductType.MIXIN -> api.getMixins()
            ProductType.SEASONING -> api.getSeasonings()
            ProductType.SHELL -> api.getShells()
        }
        return response.body()
    }

    private fun loadFromDatabase(productType: ProductType) : List<Product> {
        val storedProducts = db.productDao()
        return storedProducts.getAllOfType(productType.value).map { product -> Product(
            name = product.name,
            recipe = product.recipe,
            slug = product.slug,
            url = product.url,
        )}
    }

    private fun saveAll(products : List<Product>, productType: ProductType) {
        products.forEach { product -> db
            .productDao()
            .insert(com.example.tacos.data.entities.Product(
                slug = product.slug,
                name = product.name,
                recipe = product.recipe,
                url = product.url,
                type = productType.value
        ))}
    }

    suspend fun getRandomTaco(): Taco? {
        val response = api.getRandomTaco()
        return response.body()
    }

    fun isOnline(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}