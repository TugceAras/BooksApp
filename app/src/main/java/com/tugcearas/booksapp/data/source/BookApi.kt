package com.tugcearas.booksapp.data.source

import com.tugcearas.booksapp.data.model.BookDetailResponseModel
import com.tugcearas.booksapp.data.model.BookResponseModel
import com.tugcearas.booksapp.util.constants.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET(Constants.GET_ALL_BOOKS)
    fun getAllBooks(): Call<BookResponseModel>

    @GET(Constants.GET_BOOK_DETAIL)
    fun getDetailBook(
        @Query("id") id:Int
    ):Call<BookDetailResponseModel>
}