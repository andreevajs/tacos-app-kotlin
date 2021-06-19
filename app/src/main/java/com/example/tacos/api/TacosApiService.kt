package com.example.tacos.api

import com.example.tacos.models.Product
import com.example.tacos.models.Taco
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TacosApiService {
    @GET("random/")
    suspend fun getRandomTaco() : Response<Taco>

    @GET("mixins/")
    suspend fun getMixins() : Response<List<Product>>

    @GET("seasonings/")
    suspend fun getSeasonings() : Response<List<Product>>

    @GET("base_layers/")
    suspend fun getBaseLayers() : Response<List<Product>>

    @GET("condiments/")
    suspend fun getCondiments() : Response<List<Product>>

    @GET("shells/")
    suspend fun getShells() : Response<List<Product>>

    //@GET("contributors/{type}/{slug}")
    //suspend fun getContributors(
    //        @Path("type") type: String,
    //        @Path("slug") slug: String
    //) : Response<List<Contributor>>

    companion object {
        const val API_URL = "https://taco-randomizer.herokuapp.com/"

        fun getInstance(): TacosApiService = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TacosApiService::class.java)
    }
}