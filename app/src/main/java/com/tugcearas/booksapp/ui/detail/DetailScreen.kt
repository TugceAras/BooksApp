package com.tugcearas.booksapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tugcearas.booksapp.data.model.Book
import com.tugcearas.booksapp.data.model.BookDetailResponseModel
import com.tugcearas.booksapp.data.retrofit.RetrofitInstance
import com.tugcearas.booksapp.databinding.FragmentDetailScreenBinding
import com.tugcearas.booksapp.util.extensions.gone
import com.tugcearas.booksapp.util.extensions.loadImage
import com.tugcearas.booksapp.util.extensions.visible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailScreen : Fragment() {

    private lateinit var binding:FragmentDetailScreenBinding
    private val args : DetailScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickBackButton()
        getDetailBook(args.bookId)
    }

    private fun getDetailBook(id:Int){
        RetrofitInstance.retrofit.getDetailBook(id).enqueue(object: Callback<BookDetailResponseModel>{
            override fun onResponse(call: Call<BookDetailResponseModel>, response: Response<BookDetailResponseModel>) {

                val getDetail = response.body()?.book
                if(getDetail != null){
                    with(binding){
                        detailImageview.loadImage(getDetail.imageUrl)
                        tvDetailBookName.text = getDetail.name
                        tvAuthorName.text = getDetail.author
                        tvPublisherName.text = getDetail.publisher
                        tvPrice.text = "${getDetail.price} ₺"

                        if (getDetail.bestSeller == true) tvSellerCardview.visible()
                        else tvSellerCardview.gone()
                    }
                }
            }

            override fun onFailure(call: Call<BookDetailResponseModel>, t: Throwable) {
                Log.e("GetDetailBook", t.message.orEmpty())
            }
        })
    }

    private fun clickBackButton(){
        binding.detailToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}