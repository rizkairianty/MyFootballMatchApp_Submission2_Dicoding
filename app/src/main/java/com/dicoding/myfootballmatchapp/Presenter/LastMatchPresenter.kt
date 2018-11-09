package com.dicoding.myfootballmatchapp.Presenter

import com.dicoding.myfootballmatchapp.Api.ApiClient
import com.dicoding.myfootballmatchapp.BuildConfig
import com.dicoding.myfootballmatchapp.Response.LastMatchResponse
import retrofit2.Call
import retrofit2.Response

class LastMatchPresenter (private val view : Contract.ViewLastMatch) : Contract.Presenter{
    private var API_KEY : String? = BuildConfig.TSDB_API_KEY
    private var TEAM_ID : String? = "TEAM_ID"
    override fun showData() {
        view.showLoading()
        val apiCall = ApiClient().getInstance().LastMatch(API_KEY,TEAM_ID)

        apiCall.enqueue(object : retrofit2.Callback<LastMatchResponse>{
            override fun onFailure(call: Call<LastMatchResponse>?, t: Throwable?) {
                view.hideloading()
            }

            override fun onResponse(call: Call<LastMatchResponse>?, response: Response<LastMatchResponse>?) {
                if (response!=null){
                    if (response.isSuccessful){
                        val resData = response.body()!!.data
                        view.showLastMatch(resData)
                        view.hideloading()
                    }
                    else
                        view.hideloading()
                }
            }

        })
    }

}