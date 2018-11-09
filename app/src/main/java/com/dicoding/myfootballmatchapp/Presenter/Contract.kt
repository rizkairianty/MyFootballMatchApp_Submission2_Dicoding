package com.dicoding.myfootballmatchapp.Presenter

import com.dicoding.myfootballmatchapp.Model.LastMatchModel
import com.dicoding.myfootballmatchapp.Model.NextMatchModel
import com.dicoding.myfootballmatchapp.Model.TeamBadgeModel

interface Contract {
    interface Presenter{
        fun showData()
    }

    interface MatchPresenter{
        fun showData(idEvent : String?)
    }

    interface TeamPresenter{
        fun showTeam1(idTeam : String?)
        fun showTeam2(idTeam: String?)
    }

    interface ViewLastMatch{
        fun showLoading()
        fun hideloading()
        fun showLastMatch(data : List<LastMatchModel>)
    }

    interface ViewNextMatch{
        fun showLoading()
        fun hideLoading()
        fun showNextMatch(data : List<NextMatchModel>)
    }

    interface ViewTeamBadge{
        fun showTeam1(data: List<TeamBadgeModel>)
        fun showTeam2(data: List<TeamBadgeModel>)
    }
}