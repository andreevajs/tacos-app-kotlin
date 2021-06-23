package com.example.tacos.repositories

import android.net.ConnectivityManager
import com.example.tacos.api.TacosApiService
import com.example.tacos.data.dao.ProductDao
import com.example.tacos.data.dao.TacoDao
import com.example.tacos.data.db.AppDatabase
import com.example.tacos.data.entities.TacoWithProducts
import com.example.tacos.models.Product
import com.example.tacos.models.ProductType
import com.example.tacos.models.Taco
import javax.inject.Inject


class TacosRepository @Inject constructor(
    private val api: TacosApiService,
    private val connectivityManager: ConnectivityManager,
    db: AppDatabase
) {
    private val _tacoDao : TacoDao = db.tacoDao()
    private val _productDao : ProductDao = db.productDao()

    suspend fun getRandomTaco(): Taco? {
        if (isOnline()) {
            val request = api.getRandomTaco()
            return request.body()
        } else {
            return getSavedTacos().random()
        }
    }

    fun saveTaco(taco : Taco) {
        _productDao.insert(toEntity(taco.baseLayer, ProductType.BASE_LAYER))
        _productDao.insert(toEntity(taco.mixin, ProductType.MIXIN))
        _productDao.insert(toEntity(taco.condiment, ProductType.CONDIMENT))
        _productDao.insert(toEntity(taco.seasoning, ProductType.SEASONING))
        _productDao.insert(toEntity(taco.shell, ProductType.SHELL))
        _tacoDao.insert(toEntity(taco))
    }

    fun deleteTaco(taco: Taco) {
        _tacoDao.delete(toEntity(taco))
    }

    fun getSavedTacos() : List<Taco> {
        return _tacoDao.getAll().map { toModel(it) }
    }

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
        return _productDao.getAllOfType(productType.value).map { toModel(it) }
    }

    private fun saveAll(products : List<Product>, productType: ProductType) {
        products.forEach { _productDao.insert(toEntity(it,productType))}
    }

    private fun toEntity(taco : Taco)
    : com.example.tacos.data.entities.Taco {
        return com.example.tacos.data.entities.Taco(
            base = taco.baseLayer.slug,
            mixin = taco.mixin.slug,
            condiment = taco.condiment.slug,
            seasoning = taco.seasoning.slug,
            shell = taco.shell.slug
        )
    }

    private fun toEntity(product : Product, productType: ProductType)
            : com.example.tacos.data.entities.Product {
        return com.example.tacos.data.entities.Product(
            slug = product.slug,
            name = product.name,
            recipe = product.recipe,
            url = product.url,
            type = productType.value
        )
    }

    private fun toModel(taco : TacoWithProducts)
            : Taco {
        return Taco(
            baseLayer = toModel(taco.base),
            mixin = toModel(taco.mixin),
            condiment = toModel(taco.condiment),
            seasoning = toModel(taco.seasoning),
            shell = toModel(taco.shell)
        )
    }

    private fun toModel(product : com.example.tacos.data.entities.Product)
            : Product {
        return Product(
            name = product.name,
            recipe = product.recipe,
            slug = product.slug,
            url = product.url,
        )
    }

    private fun isOnline(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}