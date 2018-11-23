package com.dicoding.myfootballmatchapp.Presenter

import com.dicoding.myfootballmatchapp.Api.ApiRepo
import com.dicoding.myfootballmatchapp.Api.TheSportDBApi
import com.dicoding.myfootballmatchapp.Response.DetailResponse
import com.dicoding.myfootballmatchapp.Response.MatchItemResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter (private val view : Contract.ViewMatch, private val gson : Gson, private val apiRepo: ApiRepo) {
    fun showLastData(match : String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo.
            doRequest(TheSportDBApi.getLastMatch(match)),
                    MatchItemResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatch(data.events)
            }
        }
    }

    fun showNextData(match : String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo.
                    doRequest(TheSportDBApi.getNextMatch(match)),
                    MatchItemResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatch(data.events)
            }
        }
    }
}