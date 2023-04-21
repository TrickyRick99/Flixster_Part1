package com.example.flixsterpart1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
//import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
//    val titles=arrayOf("Title 1","Title 2","Title 3")
//    val descriptions=arrayOf("Testing 1","Testing 2","Testing 3")
    //private var images

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val titleItem: TextView
        private val descItem: TextView
        private val imageView: ImageView
        init {
            imageView=itemView.findViewById<ImageView>(R.id.imageView)
            titleItem= itemView.findViewById<TextView>(R.id.title)
            descItem= itemView.findViewById<TextView>(R.id.description)
        }
        fun binding(movie:Movie){
            titleItem.text=movie.title
            descItem.text=movie.descripton
            Glide.with(context).load(movie.imageUrl).into(imageView)
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {

        val context=parent.context
        val inflater = LayoutInflater.from(context)
        val movieView=inflater.inflate(R.layout.movie_list,parent,false)
        return ViewHolder(movieView)
    }

    override fun getItemCount(): Int {

        return movies.size
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {

            val movie= movies[position]
        holder.binding(movie)

    }

}