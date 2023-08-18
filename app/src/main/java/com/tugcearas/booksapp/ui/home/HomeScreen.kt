package com.tugcearas.booksapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tugcearas.booksapp.data.model.BookResponseModel
import com.tugcearas.booksapp.data.retrofit.RetrofitInstance
import com.tugcearas.booksapp.databinding.FragmentHomeScreenBinding
import com.tugcearas.booksapp.util.extensions.gone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreen : Fragment(), HomeAdapter.BookListener {

    private lateinit var binding: FragmentHomeScreenBinding
    private val homeAdapter by lazy { HomeAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.adapter = homeAdapter
        getAllBooks()
    }

    private fun getAllBooks(){
        RetrofitInstance.retrofit.getAllBooks().enqueue(object: Callback<BookResponseModel>{
            override fun onResponse(call: Call<BookResponseModel>, response: Response<BookResponseModel>)
            {
                val getBooks = response.body()?.books
                if (getBooks.isNullOrEmpty().not()){
                    homeAdapter.submitList(getBooks)
                    binding.progressBar.gone()
                }
            }

            override fun onFailure(call: Call<BookResponseModel>, t: Throwable) {
                Log.e("GetBooks", t.message.orEmpty())
            }

        })
    }

    override fun clickBookItem(id: Int) {
        val action = HomeScreenDirections.actionHomeScreenToDetailScreen(bookId = id)
        findNavController().navigate(action)
    }
}