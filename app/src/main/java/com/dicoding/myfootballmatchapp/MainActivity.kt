package com.dicoding.myfootballmatchapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.myfootballmatchapp.Fragments.LastmatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        match_navigation.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId){
                R.id.last_match ->{
                    if (savedInstanceState==null){
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.container,LastmatchFragment(),LastmatchFragment::class.simpleName)
                                .commit()
                    }
                }
                R.id.next_match ->{
                    if (savedInstanceState==null){
                        supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.container,LastmatchFragment(),LastmatchFragment::class.simpleName)
                                .commit()
                    }
                }
            }
            true
        }
    }
}
