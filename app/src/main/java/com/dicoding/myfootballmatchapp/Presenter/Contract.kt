package com.dicoding.myfootballmatchapp.Presenter

import android.widget.ProgressBar
import com.dicoding.myfootballmatchapp.Model.*

interface Contract {

    interface ViewMatch{
        fun showLoading()
        fun hideLoading()
        fun showMatch(data : List<MatchItemModel>)
    }

    interface ViewDetail{
        fun showLoading(progressBar: ProgressBar)
        fun hideLoading(progressBar: ProgressBar)
        fun showDetail(data: List<DetailModel>)
        fun showTeam1(data: List<TeamBadgeModel>)
        fun showTeam2(data: List<TeamBadgeModel>)
    }
}