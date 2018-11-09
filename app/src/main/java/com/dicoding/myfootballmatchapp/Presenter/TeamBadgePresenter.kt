package com.dicoding.myfootballmatchapp.Presenter

import android.util.Log
import com.dicoding.myfootballmatchapp.Api.ApiClient
import com.dicoding.myfootballmatchapp.BuildConfig
import com.dicoding.myfootballmatchapp.Response.TeamBadgeResponse
import retrofit2.Call
import retrofit2.Response

class TeamBadgePresenter (private val view : Contract.ViewTeamBadge) : Contract.TeamPresenter{
    private var API_KEY : String? = BuildConfig.TSDB_API_KEY

    override fun showTeam2(idTeam: String?) {
        val apiCall = ApiClient().getInstance().TeamBadge(API_KEY,idTeam)

        apiCall.enqueue(object : retrofit2.Callback<TeamBadgeResponse>{
            override fun onFailure(call: Call<TeamBadgeResponse>?, t: Throwable?) {
                Log.d("Fail","Oops")
            }

            override fun onResponse(call: Call<TeamBadgeResponse>?, response: Response<TeamBadgeResponse>?) {
                if (response!=null){
                    if (response.isSuccessful){
                        val resData = response.body()!!.data
                        view.showTeam1(resData)
                    }
                }
            }

        })
    }

    override fun showTeam1(idTeam: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}