package com.dicoding.myfootballmatchapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.dicoding.myfootballmatchapp.Api.ApiRepo
import com.dicoding.myfootballmatchapp.Model.DetailModel
import com.dicoding.myfootballmatchapp.Model.TeamBadgeModel
import com.dicoding.myfootballmatchapp.Presenter.Contract
import com.dicoding.myfootballmatchapp.Presenter.DetailPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(),Contract.ViewDetail{
    private lateinit var progressBarHomeBadge : ProgressBar
    private lateinit var progressBarAwayBadge : ProgressBar
    private lateinit var progressBar : ProgressBar
    private lateinit var detailPresenter : DetailPresenter

    private var idEvent : String = ""
    private var homeTeam : String = ""
    private var awayTeam : String = ""

    override fun showLoading(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }
    override fun showTeam1(data: List<TeamBadgeModel>) {
        Glide.with(this).load(data[0].teamBadge).into(iv_homeBadge)
        hideLoading(progressBarHomeBadge)
        iv_homeBadge.visibility = View.VISIBLE
    }

    override fun showTeam2(data: List<TeamBadgeModel>) {
        Glide.with(this).load(data[0].teamBadge).into(iv_awayBadge)
        hideLoading(progressBarAwayBadge)
        iv_awayBadge.visibility = View.VISIBLE
    }

    override fun showDetail(data: List<DetailModel>) {
        tv_schedule.text = data.get(0).matchSchedule
        teamName.text = data.get(0).homeTeam
        awayName.text = data.get(0).awayTeam
        var homeGoals = data.get(0).homeGoalDetails
        var awayGoals = data.get(0).awayGoalDetails
        home_goals.text = homeGoals?.replace(";","\n") ?: String()
        away_goals.text = awayGoals?.replace(";","\n") ?: String()
        home_formation.text = data.get(0).homeFormation
        away_formation.text = data.get(0).awayFormation
        home_score.text = data.get(0).homeScore
        away_score.text = data.get(0).awayScore
        home_red_card.text = data.get(0).homeRedCard
        away_red_card.text = data.get(0).awayRedCard
        home_yellow_card.text = data.get(0).homeYellowCard
        away_yellow_card.text = data.get(0).awayYellowCard

        hideLoading(progressBar)
        detail_field.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = applicationContext.getString(R.string.match_detail)
        idEvent = intent.getStringExtra("eventID")
        homeTeam = intent.getStringExtra("homeTeam")
        awayTeam = intent.getStringExtra("awayTeam")

        progressBarAwayBadge = pb_awayBadge
        progressBarHomeBadge = pb_homeBadge
        progressBar = pb_detail

        showLoading(progressBarHomeBadge)
        showLoading(progressBarAwayBadge)
        showLoading(progressBar)
        val gson = Gson()
        val apiRequest = ApiRepo()
        detailPresenter = DetailPresenter(this, gson, apiRequest)
        detailPresenter.showTeam1Badge(homeTeam)
        detailPresenter.showTeam2Badge(awayTeam)
        detailPresenter.showDetail(idEvent)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
