package com.example.search_weather.pojo

import com.google.gson.annotations.SerializedName

data class Wind(

    @SerializedName("speed") val speed: Double,
    @SerializedName("dec") val dec: Int

)
