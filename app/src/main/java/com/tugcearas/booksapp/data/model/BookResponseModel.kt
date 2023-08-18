package com.tugcearas.booksapp.data.model

data class BookResponseModel(
    val books: List<Book>?,
    val message: String?,
    val success: Int?
)