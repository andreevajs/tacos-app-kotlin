package com.example.tacos.di

import android.content.Context
import com.example.tacos.MainActivity
import com.example.tacos.TacosApp
import com.example.tacos.api.TacosApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class ApiModule {
    @Provides
    fun provideTacosApiService(@ApplicationContext context : Context)
    : TacosApiService = Retrofit.Builder()
        .baseUrl(TacosApiService.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TacosApiService::class.java)
}