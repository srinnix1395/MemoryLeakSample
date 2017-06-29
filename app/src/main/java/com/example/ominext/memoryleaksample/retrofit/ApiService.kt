package com.example.ominext.memoryleaksample.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ominext on 6/29/2017.
 */
interface ApiService {

    @GET("/test")
    fun callApiTest(@Query("param1") param1: String) : Observable<String>
}