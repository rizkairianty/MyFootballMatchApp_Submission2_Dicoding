package com.dicoding.myfootballmatchapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dicoding.myfootballmatchapp.Database.Favorite
import com.dicoding.myfootballmatchapp.R
import com.dicoding.myfootballmatchapp.Fragments.FavoriteFragment
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FavoriteMatchAdapter(private val context: Context, private val favMatch : MutableList<Favorite>, private val mListener : FavoriteFragment.OnFavListFragmentInteractionListener?) :
        RecyclerView.Adapter<FavoriteMatchAdapter.MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(ItemUI().createView(AnkoContext.Companion.create(parent.context,parent)))
    }

    override fun getItemCount(): Int {
        return favMatch.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(favMatch[position])
    }

    inner class MatchViewHolder (view : View) : RecyclerView.ViewHolder(view){
        private val tvTeam1 = view.find<TextView>(R.id.tv_team1)
        private val tvTeam2 = view.find<TextView>(R.id.tv_item2)
        private val tvTeam1Score: TextView = view.find(R.id.tv_team1_score)
        private val tvTeam2Score: TextView = view.find(R.id.tv_team2_score)
        private val tvSchedule : TextView = view.find(R.id.schedule)
        fun bindItem(favoriteMatch: Favorite) {
            tvTeam1.text = favoriteMatch.homeName
            tvTeam2.text = favoriteMatch.awayName
            tvTeam1Score.text = favoriteMatch.homeScore
            tvTeam2Score.text = favoriteMatch.awayScore
            tvSchedule.text = favoriteMatch.schedule

            itemView.setOnClickListener {
                mListener?.onFavListFragmentInteraction(favoriteMatch)
            }
        }
    }
}