package com.dicoding.myfootballmatchapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.myfootballmatchapp.Fragments.LastmatchFragment
import com.dicoding.myfootballmatchapp.Fragments.NextMatchFragment
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), LastmatchFragment.OnListFragmentInteractionListener,
        NextMatchFragment.OnNextListFragmentInteractionListener{
    override fun onListFragmentInteraction(item: MatchItemModel) {
        startActivity<DetailActivity>("eventID" to item.idEvent,
                "homeTeam" to item.homeTeam, "awayTeam" to item.awayTeam)
    }

    override fun onNextListFragmentInteraction(item: MatchItemModel) {
        startActivity<DetailActivity>("eventID" to item.idEvent,
                "homeTeam" to item.homeTeam, "awayTeam" to item.awayTeam)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,LastmatchFragment(),LastmatchFragment::class.simpleName)
                .commit()
        match_navigation.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId){
                R.id.last_match ->{
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.container,LastmatchFragment(),LastmatchFragment::class.simpleName)
                                .commit()

                }
                R.id.next_match ->{
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.container,NextMatchFragment(),NextMatchFragment::class.simpleName)
                                .commit()

                }
            }
            true
        }
    }
}
