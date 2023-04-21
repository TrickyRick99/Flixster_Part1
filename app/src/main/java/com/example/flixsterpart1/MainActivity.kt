package com.example.flixsterpart1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val tag= "MainActivity"
private const val MOVIE_URL="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1"
class MainActivity : AppCompatActivity() {
    private val movies= mutableListOf<Movie>()
    private lateinit var movieRev: RecyclerView

//    private var adapt: RecyclerView.Adapter<MovieAdapter.ViewHolder>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        movieRev= findViewById<RecyclerView>(R.id.movieRev)
        //For adapter
         val adapt=MovieAdapter(this,movies)
         movieRev.adapter=adapt

         val layoutManager=LinearLayoutManager(this)
         movieRev.layoutManager=layoutManager

        val client = AsyncHttpClient()
        client.get(MOVIE_URL, object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(tag, "Error $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(tag, "Success JSON data $json")
                try{
                    val movieList=json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.jsonList(movieList))
                    adapt.notifyDataSetChanged()
                    Log.i(tag, "Movie List $movies")
                }
                catch(except:JSONException){
                    Log.e(tag,"Exception found $except")
                }

            }

        })



    }

}