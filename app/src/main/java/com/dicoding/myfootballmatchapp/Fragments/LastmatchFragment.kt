package com.dicoding.myfootballmatchapp.Fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.dicoding.myfootballmatchapp.Adapter.LastMatchAdapter
import com.dicoding.myfootballmatchapp.Api.ApiRepo
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import com.dicoding.myfootballmatchapp.Presenter.Contract
import com.dicoding.myfootballmatchapp.Presenter.MatchPresenter

import com.dicoding.myfootballmatchapp.R.color.colorAccent
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*
private const val LEAGUE_ID = "4335"

/**
 * A simple [Fragment] subclass.
 *
 */
class LastmatchFragment : Fragment(), Contract.ViewMatch {
    private var lastmatchList: MutableList<MatchItemModel> = mutableListOf()
    private lateinit var adapter: LastMatchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: MatchPresenter
    private lateinit var spinner: Spinner
    private var listener: OnListFragmentInteractionListener? = null
    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatch(data: List<MatchItemModel>) {
        swipeRefresh.isRefreshing = false
        lastmatchList.clear()
        lastmatchList.addAll(data)
        adapter.notifyDataSetChanged()
        hideLoading()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_lastmatch, container, false)
        return createView(AnkoContext.Companion.create(ctx))
    }

    private fun createView(ui : AnkoContext<Context>) : View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            //spinner = spinner ()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    recyclerView = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }

        adapter = LastMatchAdapter(ctx, lastmatchList, listener)
        recyclerView.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.showLastData(LEAGUE_ID)
        }

        showLoading()
        val apiRequest = ApiRepo()
        val gson = Gson()
        //presenter = LastMatchPresenter(this@LastmatchFragment)
        presenter = MatchPresenter(this@LastmatchFragment, gson, apiRequest)
        presenter.showLastData(LEAGUE_ID)
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: MatchItemModel)
    }
}
