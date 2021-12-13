package com.example.lokalinterview.api

import com.example.lokalinterview.Response.article
import retrofit2.Response
import retrofit2.http.GET

interface listing {
    @GET("albums/1/photo")
    suspend fun Searchnews(

    ): Response<MutableList<article>>
}