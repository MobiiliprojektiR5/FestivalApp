package com.example.search_weather.pojo

import com.google.gson.annotations.SerializedName

data class Weather (

    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String

)