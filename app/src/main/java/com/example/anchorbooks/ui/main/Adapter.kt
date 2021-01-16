package com.example.anchorbooks.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.anchorbooks.R
import com.example.anchorbooks.pojo.Books
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_books.view.*


class Adapter(var mdataSetP: List<Books>, var listenerS: MainFragment): RecyclerView.Adapter<Adapter.photoHolder>() {



    fun updateData(listBooks: List<Books>) {
        Log.d("UPDATE", "update ${listBooks.size}")
        mdataSetP = listBooks
        notifyDataSetChanged()
    }
    class photoHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTv= itemView.titleTV
        val photoTv= itemView.photoTV

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): photoHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_books, parent, false)

        return photoHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("Cantidad",mdataSetP.size.toString())
        return mdataSetP.size
    }

    override fun onBindViewHolder(holder: photoHolder, position: Int) {
        val photo =  mdataSetP[position]

        val titletv ="${photo.title}"

        holder.titleTv.text = titletv
        Picasso.get()
            .load(photo.imageLink)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.photoTv)

        holder.itemView.setOnClickListener(View.OnClickListener{

            Toast.makeText(holder.itemView.context," $titletv", Toast.LENGTH_SHORT).show()
            listenerS.onItemClick(mdataSetP.get(position))

        })


    }




    interface MyClickListener {

        fun onItemClick(books: Books)

    }

    interface IAdapter{
        fun getFromAdapter(id:Int)

    }
}