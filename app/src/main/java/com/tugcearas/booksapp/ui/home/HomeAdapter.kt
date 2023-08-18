package com.tugcearas.booksapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.booksapp.data.model.Book
import com.tugcearas.booksapp.databinding.BookItemBinding
import com.tugcearas.booksapp.util.extensions.gone
import com.tugcearas.booksapp.util.extensions.loadImage
import com.tugcearas.booksapp.util.extensions.visible

class HomeAdapter(
    private val bookListener: BookListener
) : ListAdapter<Book, HomeAdapter.BookViewHolder>(DiffCallBack()) {

    inner class BookViewHolder(
        private val binding: BookItemBinding,
        private val bookListener: BookListener
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(book:Book) = with(binding){
            tvItemBookName.text = book.name
            tvItemPrice.text = "${book.price} ₺"

            itemImageview.loadImage(book.imageUrl)

            root.setOnClickListener {
                bookListener.clickBookItem(book.id ?: 1)
            }

            if (book.isBestSeller == false) tvItemInfo.gone()
            else tvItemInfo.visible()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), bookListener
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) = holder.bind(getItem(position))

    class DiffCallBack: DiffUtil.ItemCallback<Book>(){
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

    interface BookListener {
        fun clickBookItem(id: Int)
    }
}