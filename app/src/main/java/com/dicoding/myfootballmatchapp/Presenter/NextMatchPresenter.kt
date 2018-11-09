package com.dicoding.myfootballmatchapp.Presenter

import com.dicoding.myfootballmatchapp.Api.ApiClient
import com.dicoding.myfootballmatchapp.BuildConfig
import com.dicoding.myfootballmatchapp.Response.NextMatchResponse
import retrofit2.Call
import retrofit2.Response

class NextMatchPresenter(private val view : Contract.ViewNextMatch) : Contract.Presenter {
    private var API_KEY : String? = BuildConfig.TSDB_API_KEY
    private var TEAM_ID : String? = "TEAM_ID"

    override fun showData() {
        view.showLoading()
        val apiCall = ApiClient().getInstance().NextMatch(API_KEY,TEAM_ID)
        apiCall.enqueue(object : retrofit2.Callback<NextMatchResponse>{
            override fun onFailure(call: Call<NextMatchResponse>?, t: Throwable?) {
                view.hideLoading()
            }

            override fun onResponse(call: Call<NextMatchResponse>?, response: Response<NextMatchResponse>?) {
                if (response!=null){
                    if (response.isSuccessful){
                        val resData = response.body()!!.data
                        view.showNextMatch(resData)
                        view.hideLoading()
                    }
                    else{
                        view.hideLoading()
                    }
                }
            }

        })
    }

}