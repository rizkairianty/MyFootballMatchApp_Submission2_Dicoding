package com.dicoding.myfootballmatchapp.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dicoding.myfootballmatchapp.R
import android.content.Context
import android.util.Log
import com.dicoding.myfootballmatchapp.Fragments.LastmatchFragment
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class LastMatchAdapter (private val context: Context, private val lastMatch : MutableList<MatchItemModel>, private val mListener: LastmatchFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<LastMatchAdapter.MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MatchViewHolder {
        return MatchViewHolder(ItemUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return lastMatch.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(lastMatch[position])
    }

    inner class MatchViewHolder (view : View) : RecyclerView.ViewHolder(view){
        private val tvTeam1 = view.find<TextView>(R.id.tv_team1)
        private val tvTeam2 = view.find<TextView>(R.id.tv_item2)
        val tvTeam1Score: TextView = view.find(R.id.tv_team1_score)
        val tvTeam2Score: TextView = view.find(R.id.tv_team2_score)
        private val tvSchedule : TextView = view.find(R.id.schedule)
        fun bindItem(lastmatch: MatchItemModel) {
            tvTeam1.text = lastmatch.homeTeam
            tvTeam2.text = lastmatch.awayTeam
            tvTeam1Score.text = lastmatch.homeScore
            tvTeam2Score.text = lastmatch.awayScore
            val idHomeTeam: String? = lastmatch.idHomeTeam
            tvSchedule.text = lastmatch.dateEvent
            val idAwayTeam: String? = lastmatch.idAwayTeam

            itemView.setOnClickListener {
                mListener?.onListFragmentInteraction(lastmatch)
                Log.d("Wowow","Wowow")
            }
        }
        }
    }