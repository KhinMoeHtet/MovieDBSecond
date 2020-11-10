package com.kmh.moviedb.ui.topRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kmh.moviedb.R
import com.kmh.moviedb.model.ResultsItem
import com.kmh.moviedb.ui.nowPlaying.MovieAdapter
import com.kmh.moviedb.ui.nowPlaying.NowPlayingFragmentDirections
import com.kmh.moviedb.ui.nowPlaying.NowPlayingViewModel
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment(),MovieAdapter.OnClickListener  {

    private lateinit var topRatedViewModel: TopRatedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_top_rated, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedViewModel=ViewModelProvider(this).get(TopRatedViewModel::class.java)

        var topRatedAdapter = MovieAdapter()

        topRatedRecycler.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter=topRatedAdapter
        }
        topRatedAdapter.setOnClickListener(this)

        topRatedViewModel.getTopRatedArticle().observe(
            viewLifecycleOwner, Observer { movies->
                topRatedAdapter.updateArticle(movies.results as List<ResultsItem>)

            }
        )

    }

    override fun onResume() {
        super.onResume()
        topRatedViewModel.loadTopRatedData()
    }

    override fun onClick(item: ResultsItem) {
        val directions= TopRatedFragmentDirections.actionNavTopRatedToTopRatedDetailFragment(item)
        view?.findNavController()?.navigate(directions)
    }

}