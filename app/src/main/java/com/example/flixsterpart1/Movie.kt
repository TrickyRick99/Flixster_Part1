package com.example.flixsterpart1

import org.json.JSONArray

data class Movie(
    val title:String,
    val descripton: String,
    private val image: String
) {
    val imageUrl="https://image.tmdb.org/t/p/w500/$image"
    companion object{
        fun jsonList(movieList: JSONArray):List<Movie> {
            val movies= mutableListOf<Movie>()

            for (x in 0 until movieList.length()){
                val movJson = movieList.getJSONObject(x)
                movies.add(
                    Movie(
                        movJson.getString("title"),
                        movJson.getString("overview"),
                        movJson.getString("poster_path")

                    )
                )
            }
            return movies
        }
    }
}