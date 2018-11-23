package com.dicoding.myfootballmatchapp.Fragments


import android.content.AbstractThreadedSyncAdapter
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
import com.dicoding.myfootballmatchapp.Adapter.NextMatchAdapter
import com.dicoding.myfootballmatchapp.Api.ApiRepo
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import com.dicoding.myfootballmatchapp.Presenter.MatchPresenter

import com.dicoding.myfootballmatchapp.R
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.annotations.Contract

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val LEAGUE_ID = "4335"

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), com.dicoding.myfootballmatchapp.Presenter.Contract.ViewMatch {
    private var nextMatchList : MutableList<MatchItemModel> = mutableListOf()
    private lateinit var progressBar : ProgressBar
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var recyclerView : RecyclerView
    private lateinit var presenter : MatchPresenter
    private lateinit var adapter: NextMatchAdapter
    private var listener : OnNextListFragmentInteractionListener? = null
    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatch(data: List<MatchItemModel>) {
        swipeRefresh.isRefreshing = false
        nextMatchList.clear()
        nextMatchList.addAll(data)
        adapter.notifyDataSetChanged()
        hideLoading()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_next_match, container, false)
        return createView(AnkoContext.Companion.create(ctx))
    }

    private fun createView(ui : AnkoContext<Context>) : View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            //spinner = spinner ()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
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

        adapter = NextMatchAdapter(ctx, nextMatchList, listener)
//        adapter = LastMatchAdapter(ctx, lastmatchList){
//            startActivity<DetailActivity>()
        recyclerView.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.showNextData(LEAGUE_ID)
        }
        showLoading()
        val apiRequest = ApiRepo()
        val gson = Gson()
        //presenter = LastMatchPresenter(this@LastmatchFragment)
        presenter = MatchPresenter(this@NextMatchFragment, gson, apiRequest)
        presenter.showNextData(LEAGUE_ID)
        //presenter.showData()
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNextListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    interface OnNextListFragmentInteractionListener {
        fun onNextListFragmentInteraction(item: MatchItemModel)
    }

}
