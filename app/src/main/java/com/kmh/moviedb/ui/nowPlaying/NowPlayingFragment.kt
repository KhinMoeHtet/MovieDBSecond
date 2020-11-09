package com.kmh.moviedb.ui.nowPlaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kmh.moviedb.R

class NowPlayingFragment : Fragment() {

    private lateinit var nowPlayingViewModel: NowPlayingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        nowPlayingViewModel =
                ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_now_playing, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        nowPlayingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}