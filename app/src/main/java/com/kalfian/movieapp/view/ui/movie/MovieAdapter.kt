package com.kalfian.movieapp.view.ui.movie

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.R
import com.kalfian.movieapp.model.ResponseMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter(val items: List<ResponseMovie.ResultMovie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(pos : Int)
    }

    fun setOnItemClickListener(itemClick: OnItemClickListener) {
        this.itemClickListener = itemClick
    }

    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_list, view, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        view.binding(items.get(position), itemClickListener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun binding(
            data: ResponseMovie.ResultMovie,
            itemClickListener: OnItemClickListener
        ) {
            itemView.textview_title_list.text = data.title
            itemView.textview_description_list.text = data.overview
            Picasso.get().load(BuildConfig.MOVIE_PATH + data.poster_path).into(itemView.imageview_list)

            itemView.setOnClickListener {
                val pos : Int = adapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    itemClickListener.onItemClick(pos)
                }
            }
        }
    }
}