package com.dicoding.myfootballmatchapp.Adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dicoding.myfootballmatchapp.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ItemUI : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(5)
                    //radius = 4f
                    //background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                    textView {
                        gravity = Gravity.CENTER_HORIZONTAL
                        text = context.getString(R.string.schedule)
                        id = R.id.schedule
                        bottomMargin = dip(5)
                        backgroundColor = R.color.taupe
                        textColor = Color.WHITE
                    }.lparams(width = matchParent, height = wrapContent)

                    linearLayout {
                        lparams(width = matchParent, height = matchParent)
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 3f
                        padding = dip(20)

                        linearLayout {
                            lparams(width = dip(0), height = matchParent, weight = 1f)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER

                            textView {
                                id = R.id.tv_team1
                                text = "Team 1"
                            }.lparams {
                            }
                        }
                        linearLayout {
                            lparams(width = dip(0), height = matchParent, weight = 1f)
                            orientation = LinearLayout.VERTICAL

                            linearLayout {
                                lparams(width = matchParent, height = matchParent)
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3f

                                linearLayout {
                                    lparams(width = matchParent, height = matchParent, weight = 1f)
                                    orientation = LinearLayout.VERTICAL
                                    gravity = Gravity.CENTER

                                    textView {
                                        id = R.id.tv_team1_score
                                        text = "Score"
                                        typeface = Typeface.DEFAULT_BOLD
                                        textSize = 20f
                                    }.lparams {
                                        gravity = Gravity.END
                                    }

                                }
                                linearLayout {
                                    lparams(width = matchParent, height = matchParent, weight = 1f)
                                    orientation = LinearLayout.VERTICAL
                                    gravity = Gravity.CENTER

                                    textView {
                                        text = ":"
                                        textSize = 20f
                                        textColor = Color.RED
                                        marginStart = dip(5)
                                        marginEnd = dip(5)
                                    }.lparams {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }
                                }
                                linearLayout {
                                    lparams(width = matchParent, height = matchParent, weight = 1f)
                                    orientation = LinearLayout.VERTICAL
                                    gravity = Gravity.CENTER

                                    textView {
                                        id = R.id.tv_team2_score
                                        text = "Score"
                                        typeface = Typeface.DEFAULT_BOLD
                                        textSize = 20f
                                    }.lparams {
                                        gravity = Gravity.START
                                    }
                                }
                            }

                        }
                        linearLayout {
                            lparams(width = dip(0), height = matchParent, weight = 1f)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER
                            textView {
                                id = R.id.tv_item2
                                text = "Team 2"
                            }.lparams {
                            }
                        }
                    }
                }
            }
            }
        }


}