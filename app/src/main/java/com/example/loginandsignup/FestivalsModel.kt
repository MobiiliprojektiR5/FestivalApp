package com.example.loginandsignup

import kotlinx.serialization.Serializable

@Serializable
data class Festival(
    val Names: Map<String, FestivalDetails> = emptyMap()
) {
    // Non-argument constructor
    constructor() : this(emptyMap())
}

@Serializable
data class FestivalDetails(
    val dateend: String = "",
    val datestart: String = "",
    val imageurl: String = "",
    val infotext: String = "",
    val locationaddress: String = "",
    val locationtown: String = "",
    val name: String = "",
    val ticket: String = "",
    val webpage: String = ""
) {
    // Non-argument constructor
    constructor() : this("", "", "", "", "", "", "", "", "")
}