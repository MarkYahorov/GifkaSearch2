package com.example.gifkasearch.data

import com.google.gson.annotations.SerializedName

data class ThisData(
    @SerializedName("data")
    val data: List<DataX>
)