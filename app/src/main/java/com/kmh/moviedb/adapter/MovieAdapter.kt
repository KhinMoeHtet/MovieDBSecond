package com.kmh.moviedb.ui.nowPlaying

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmh.moviedb.R
import com.kmh.moviedb.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_now_playing.view.*


class MovieAdapter:RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var articlesItems:List<ResultsItem> = ArrayList()
    var clickListener:OnClickListener?= null

    inner class MovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        lateinit var item:ResultsItem

        fun bind(articlesItem:ResultsItem){
            this.item=articlesItem
            itemView.movieTitle.text=articlesItem.originalTitle
            Picasso.get()
                .load("http://image.tmdb.org/t/p/w500"+articlesItem.posterPath)
                .into(itemView.movieImage)
        }


        override fun onClick(v: View?) {
            clickListener?.onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_now_playing,parent,false)
        return MovieViewHolder(view)
    }


    override fun getItemCount(): Int =articlesItems.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(articlesItems[position])
    }

    fun updateArticle(articleList:List<ResultsItem>){
        this.articlesItems=articleList
        notifyDataSetChanged()
    }

    interface OnClickListener{
        fun onClick(item:ResultsItem)
    }

    fun setOnClickListener(clickListener:OnClickListener){
        this.clickListener=clickListener
    }

}



