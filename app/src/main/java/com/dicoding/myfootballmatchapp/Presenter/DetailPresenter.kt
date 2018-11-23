package com.dicoding.myfootballmatchapp.Presenter

import com.dicoding.myfootballmatchapp.Api.ApiRepo
import com.dicoding.myfootballmatchapp.Api.TheSportDBApi
import com.dicoding.myfootballmatchapp.Response.DetailResponse
import com.dicoding.myfootballmatchapp.Response.MatchItemResponse
import com.dicoding.myfootballmatchapp.Response.TeamBadgeResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter (private val view : Contract.ViewDetail, private val gson : Gson, private val apiRepo: ApiRepo) {
    fun showDetail(match : String?){
        doAsync {
            val data = gson.fromJson(apiRepo.
                    doRequest(TheSportDBApi.getMatchDetail(match)),
                    DetailResponse::class.java
            )

            uiThread {
                view.showDetail(data.events)
            }
        }
    }

    fun showTeam1Badge(badge : String?){
        doAsync {
            val teamBadge = gson.fromJson(apiRepo.
            doRequest(TheSportDBApi.getBadgeUrl(badge)),
                    TeamBadgeResponse::class.java
            )

            uiThread {
                view.showTeam1(teamBadge.teams)
            }
        }
    }

    fun showTeam2Badge(badge : String?){
        doAsync {
            val teamBadge = gson.fromJson(apiRepo.
                    doRequest(TheSportDBApi.getBadgeUrl(badge)),
                    TeamBadgeResponse::class.java
            )

            uiThread {
                view.showTeam2(teamBadge.teams)
            }
        }
    }

}