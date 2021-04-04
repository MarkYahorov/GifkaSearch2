package com.example.gifkasearch.api

import com.example.gifkasearch.data.ThisData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/search?api_key=pa7TbCo7bJw38zJwAy7Agfcl3mV6t8HH&q=ass&limit=12")
    fun getGifts(): Call<ThisData>

}