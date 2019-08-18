package com.kalfian.movieapp.view.ui.tvShow

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.R
import com.kalfian.movieapp.model.ResponseTvShow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class TvShowAdapter(val items: List<ResponseTvShow.ResultTvShow>): RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(view: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_list, view, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        view.binding(items[position], itemClickListener)
    }

    fun setOnItemClickListener(itemClick: OnItemClickListener) {
        this.itemClickListener = itemClick
    }

    interface OnItemClickListener {
        fun onItemClickListener(position: Int)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun binding(
            data: ResponseTvShow.ResultTvShow,
            itemClickListener: OnItemClickListener
        ) {
            itemView.textview_title_list.text = data.title
            itemView.textview_description_list.text = data.overview
            Picasso.get().load(BuildConfig.MOVIE_PATH + data.poster_path).into(itemView.imageview_list)

            itemView.setOnClickListener {
                val pos : Int = adapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    itemClickListener.onItemClickListener(pos)
                }
            }
        }
    }
}