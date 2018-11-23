package com.dicoding.myfootballmatchapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dicoding.myfootballmatchapp.Fragments.NextMatchFragment
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import com.dicoding.myfootballmatchapp.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class NextMatchAdapter (private val context: Context, private val nextMatch : MutableList<MatchItemModel>, private val mListener: NextMatchFragment.OnNextListFragmentInteractionListener?) : RecyclerView.Adapter<NextMatchAdapter.MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MatchViewHolder {
        return MatchViewHolder(ItemUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return nextMatch.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(nextMatch[position])
    }

    inner class MatchViewHolder (view : View) : RecyclerView.ViewHolder(view){
        private val tvTeam1 : TextView = view.find(R.id.tv_team1)
        private val tvTeam2 : TextView = view.find(R.id.tv_item2)
        val tvTeam1Score : TextView = view.find(R.id.tv_team1_score)
        val tvTeam2Score : TextView = view.find(R.id.tv_team2_score)
        private val tvSchedule : TextView = view.find(R.id.schedule)
        fun bindItem(matchItem: MatchItemModel) {
            tvTeam1.text = matchItem.homeTeam
            tvTeam2.text = matchItem.awayTeam
            tvTeam1Score.text = "-"
            tvTeam2Score.text = "-"

            itemView.setOnClickListener {
                mListener?.onNextListFragmentInteraction(matchItem)
            }
        }
    }
}