package com.example.gifkasearch.api

import com.example.gifkasearch.data.ThisData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search?api_key=pa7TbCo7bJw38zJwAy7Agfcl3mV6t8HH")
    suspend fun getGifts(@Query("q") request: String): ThisData

}