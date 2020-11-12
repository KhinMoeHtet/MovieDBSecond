package com.kmh.moviedb.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.kmh.moviedb.R
import com.kmh.moviedb.model.SearchModel
import com.kmh.moviedb.model.SearchResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_now_playing.view.*
import kotlinx.android.synthetic.main.item_search.view.*


class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchMovieViewHolder>() {

    private var articlesItems:List<SearchResultsItem> = ArrayList()
    var clickListener:OnClickListener?= null

    inner class SearchMovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
            View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        lateinit var item:SearchResultsItem

        fun bind(articlesItem:SearchResultsItem){
            this.item=articlesItem
            itemView.searchMovieTitle.text=articlesItem.originalTitle
            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w500"+articlesItem.posterPath)
                    .into(itemView.searchMovieImage)
        }


        override fun onClick(v: View?) {
            clickListener?.onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        var view=LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search,parent,false)
        return SearchMovieViewHolder(view)
    }


    override fun getItemCount(): Int =articlesItems.size

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bind(articlesItems[position])
    }

    fun updateArticle(articleList:List<SearchResultsItem>){
        this.articlesItems=articleList
        notifyDataSetChanged()
    }


    private var movieLists:MutableLiveData<SearchModel> = MutableLiveData()

    fun setWord(keyword: String):MutableLiveData<SearchModel> = movieLists



    interface OnClickListener{
        fun onClick(item:SearchResultsItem)
    }

    fun setOnClickListener(clickListener:OnClickListener){
        this.clickListener=clickListener
    }

    fun setWord(wordList:List<SearchResultsItem>){
        this.articlesItems=wordList
        notifyDataSetChanged()
    }

}



