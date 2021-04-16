package app.bo.com.ucb.cleanucb2021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.bo.com.ucb.domain.Movie
import com.squareup.picasso.Picasso

class MainAdapter(val list: List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val view =  LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = list.get(position)
        holder.itemView.findViewById<TextView>(R.id.textViewTitle).text = movie.title
        Picasso.get().load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
            .into(holder.itemView.findViewById<ImageView>(R.id.imageViewPosterPath))

    }
}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
