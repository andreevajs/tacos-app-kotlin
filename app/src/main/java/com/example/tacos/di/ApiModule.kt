package com.example.tacos.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.tacos.api.TacosApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideTacosApiService()
    : TacosApiService = Retrofit.Builder()
        .baseUrl(TacosApiService.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TacosApiService::class.java)

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context)
    : ConnectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}