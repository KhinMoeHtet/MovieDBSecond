package com.kmh.moviedb.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmh.moviedb.R
import com.kmh.moviedb.adapter.SearchAdapter
import com.kmh.moviedb.model.SearchResultsItem
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_search2.*

class SearchFragment : Fragment() {

    lateinit var searchView: SearchView
    lateinit var queryTextListener: SearchView.OnQueryTextListener


    private lateinit var searchViewModel: SearchViewModel
    var searchAdapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search2, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter= SearchAdapter()
        searchViewModel=ViewModelProvider(this).get(SearchViewModel::class.java)


        searchRecycler.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter=searchAdapter
        }


        searchViewModel.getSearchArticle().observe(
                viewLifecycleOwner, Observer { movies->
            searchAdapter.setWord(movies.results as List<SearchResultsItem>)

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)

        val searchItem = menu.findItem(R.id.action_settings)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName));
            searchView.queryHint = "Search movie"
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i("TextChange: >>>>>>", query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    var searchQuery = "%$newText%"
                    searchViewModel.getSearchWord(searchQuery!!).observe(
                            viewLifecycleOwner, Observer {
                        Log.d("SEARCH>>>>>>", searchQuery)
                        Log.d("SEARCH>>>>>>", it.toString())
                        searchAdapter.setWord(it.results as List<SearchResultsItem>)
                        //searchViewModel.loadSearchData(searchQuery)

                    }
                    )
                    searchRecycler.apply {
                        layoutManager= GridLayoutManager(context,2)
                        adapter=searchAdapter
                    }

                    searchViewModel.loadSearchData(searchQuery)

                    return true
                }

            })
        }
    }

    override fun onResume() {
        super.onResume()

        searchViewModel.loadSearchData("query")
    }


}