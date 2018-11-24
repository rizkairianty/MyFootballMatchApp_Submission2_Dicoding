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
import android.widget.TextView
import com.dicoding.myfootballmatchapp.Adapter.FavoriteMatchAdapter
import com.dicoding.myfootballmatchapp.Adapter.LastMatchAdapter
import com.dicoding.myfootballmatchapp.Api.ApiRepo
import com.dicoding.myfootballmatchapp.Database.Favorite
import com.dicoding.myfootballmatchapp.Database.database
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import com.dicoding.myfootballmatchapp.Presenter.Contract
import com.dicoding.myfootballmatchapp.Presenter.MatchPresenter
import com.dicoding.myfootballmatchapp.R
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

private const val LEAGUE_ID = "4335"

class FavoriteFragment : Fragment(), AnkoComponent<Context> {
    private var favMatchList: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private var listener : FavoriteFragment.OnFavListFragmentInteractionListener? = null
/*    override override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteMatchAdapter(favMatchList){
            context?.star
        }
    }*/
    /* override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatch(data: List<MatchItemModel>) {
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }
*/
    private fun showFavorite(){
        favMatchList.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val res = select(Favorite.TABLE_FAVORITE)
            val favorite = res.parseList(classParser<Favorite>())
            favMatchList.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(ctx))
    }

    override fun createView(ui : AnkoContext<Context>) : View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    recyclerView = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }

        adapter = FavoriteMatchAdapter(ctx, favMatchList, listener)
        recyclerView.adapter = adapter

        swipeRefresh.onRefresh {
            showFavorite()
        }

        /*val apiRequest = ApiRepo()
        val gson = Gson()
        //presenter = LastMatchPresenter(this@LastmatchFragment)
        presenter = MatchPresenter(this@FavoriteFragment, gson, apiRequest)
        presenter.showLastData(LEAGUE_ID)*/
        return view
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFavListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFavListFragmentInteractionListener {
        fun onFavListFragmentInteraction(item: Favorite)
    }

}
