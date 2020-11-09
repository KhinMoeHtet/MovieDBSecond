package com.kmh.moviedb.ui.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kmh.moviedb.R
import com.kmh.moviedb.ui.topRated.TopRatedViewModel

class UpcomingFragment : Fragment() {

    private lateinit var upcomingViewModel: UpcomingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        upcomingViewModel =
            ViewModelProvider(this).get(UpcomingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_upcoming, container, false)
        val textView: TextView = root.findViewById(R.id.text_upcoming)
        upcomingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

}