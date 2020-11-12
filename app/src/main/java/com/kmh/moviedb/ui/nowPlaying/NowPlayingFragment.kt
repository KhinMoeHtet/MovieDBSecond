package com.kmh.moviedb.ui.nowPlaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kmh.moviedb.R
import com.kmh.moviedb.model.ResultsItem
import kotlinx.android.synthetic.main.fragment_now_playing.*

class NowPlayingFragment : Fragment(),MovieAdapter.OnClickListener{

    private lateinit var nowPlayingViewModel: NowPlayingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        nowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_now_playing, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingViewModel=ViewModelProvider(this).get(NowPlayingViewModel::class.java)

        var nowPlayingAdapter = MovieAdapter()

        nowPlayingRecycler.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=nowPlayingAdapter
        }
        nowPlayingAdapter.setOnClickListener(this)

        nowPlayingViewModel.getNowPlayingArticle().observe(
         viewLifecycleOwner, Observer { movies->
          nowPlayingAdapter.updateArticle(movies.results as List<ResultsItem>)

        }
      )
        btnClick.setOnClickListener{
            findNavController().navigate(R.id.action_nav_now_playing_to_searchFragment2)
        }

    }

    override fun onResume() {
        super.onResume()
        nowPlayingViewModel.loadData()
    }

    override fun onClick(item: ResultsItem) {
        val directions= NowPlayingFragmentDirections.actionNavNowPlayingToNowPlayingDetailsFragment(item)
        view?.findNavController()?.navigate(directions)
    }



}