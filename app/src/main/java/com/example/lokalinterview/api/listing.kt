package com.example.lokalinterview.api

import com.example.lokalinterview.Response.article
import retrofit2.Response
import retrofit2.http.GET

interface listing {
//    1st step
    @GET("photos")
    suspend fun Searchnews(): Response<MutableList<article>>
}