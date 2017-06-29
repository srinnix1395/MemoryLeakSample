package com.example.ominext.memoryleaksample.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ominext on 6/29/2017.
 */

object RetrofitClient{
    fun getApiService(): ApiService {
        return Retrofit.Builder()
                .baseUrl("http://facebook.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}