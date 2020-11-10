package com.kmh.moviedb.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kmh.moviedb.R
import com.kmh.moviedb.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_now_playing_details.*
import kotlinx.android.synthetic.main.item_now_playing.view.*

class NowPlayingDetailsFragment : Fragment() {

    private val args: NowPlayingDetailsFragmentArgs by navArgs()
    private lateinit var item:ResultsItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item=args.itemDetails

        movieDetailTitle.text=item.originalTitle
        voteCount.text=item.voteCount.toString()
        overviewDetail.text=item.overview

        Picasso.get()
            .load("http://image.tmdb.org/t/p/w500"+item.posterPath)
            .into(movieDetailImage)
    }

}