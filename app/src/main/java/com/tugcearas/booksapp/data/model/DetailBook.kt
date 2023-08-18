package com.tugcearas.booksapp.data.model

import com.google.gson.annotations.SerializedName

data class DetailBook(

    val author: String?,
    val id: Int?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("best_seller") val bestSeller: Boolean?,
    val name: String?,
    val price: Double?,
    val publisher: String?
)
