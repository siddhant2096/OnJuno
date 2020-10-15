package com.example.onjuno.API


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("photos")
    val photos: List<Photo>
)