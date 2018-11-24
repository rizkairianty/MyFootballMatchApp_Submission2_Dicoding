package com.dicoding.myfootballmatchapp

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.dicoding.myfootballmatchapp.Api.ApiRepo
import com.dicoding.myfootballmatchapp.Database.Favorite
import com.dicoding.myfootballmatchapp.Database.database
import com.dicoding.myfootballmatchapp.Model.DetailModel
import com.dicoding.myfootballmatchapp.Model.TeamBadgeModel
import com.dicoding.myfootballmatchapp.Presenter.Contract
import com.dicoding.myfootballmatchapp.Presenter.DetailPresenter
import com.dicoding.myfootballmatchapp.R.menu.detail_menu
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(),Contract.ViewDetail{
    private lateinit var progressBarHomeBadge : ProgressBar
    private lateinit var progressBarAwayBadge : ProgressBar
    private lateinit var progressBar : ProgressBar
    private lateinit var detailPresenter : DetailPresenter
    private lateinit var match : DetailModel

    private var menuItem : Menu? = null
    private var isFavorite : Boolean = false
    private var isDataLoaded : Boolean = false
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
        match = data[0]
        tv_schedule.text = data[0].matchSchedule
        teamName.text = data[0].homeTeam
        awayName.text = data[0].awayTeam
        val homeGoals = data[0].homeGoalDetails
        val awayGoals = data[0].awayGoalDetails
        home_goals.text = homeGoals?.replace(";","\n") ?: String()
        away_goals.text = awayGoals?.replace(";","\n") ?: String()
        home_formation.text = data[0].homeFormation
        away_formation.text = data[0].awayFormation
        home_score.text = data[0].homeScore
        away_score.text = data[0].awayScore
        home_red_card.text = data[0].homeRedCard
        away_red_card.text = data[0].awayRedCard
        home_yellow_card.text = data[0].homeYellowCard
        away_yellow_card.text = data[0].awayYellowCard

        hideLoading(progressBar)
        detail_field.visibility = View.VISIBLE
        isDataLoaded = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = resources.getString(R.string.match_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = applicationContext.getString(R.string.match_detail)
        idEvent = intent.getStringExtra("eventID")
        homeTeam = intent.getStringExtra("homeTeam")
        awayTeam = intent.getStringExtra("awayTeam")

        progressBarAwayBadge = pb_awayBadge
        progressBarHomeBadge = pb_homeBadge
        progressBar = pb_detail

        favoriteState()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu,menu)
        menuItem = menu
        setFavorite(isFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if(isDataLoaded) {
                    if (isFavorite)
                        removeFromFavorite()
                    else
                        addToFavorite()
                    //isFavorite = !isFavorite
                    setFavorite(isFavorite)
                }else{
                    toast(resources.getString(R.string.data_not_loaded))
                }
                    true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to match.eventId,
                        Favorite.HOME_NAME to match.homeTeam,
                        Favorite.AWAY_NAME to match.awayTeam,
                        Favorite.HOME_SCORE to match.homeScore,
                        Favorite.AWAY_SCORE to match.awayScore,
                        Favorite.SCHEDULE to match.matchSchedule)
            }
            toast(resources.getString(R.string.added_to_fav))
            isFavorite = true
        }catch (e : SQLiteConstraintException){
            Log.d("Fail", "Fail")
        }
    }

    private fun setFavorite(isFav : Boolean){
        if (isFav)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_is_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,R.drawable.ic_not_favorite)
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE
                        , "(EVENT_ID = {eventId})"
                        , "eventId" to idEvent)
            }
            toast(resources.getString(R.string.remove_fav))
            isFavorite = false
        } catch (e : SQLiteConstraintException){
            Log.d("Fail", "Fail")
        }
    }
    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
