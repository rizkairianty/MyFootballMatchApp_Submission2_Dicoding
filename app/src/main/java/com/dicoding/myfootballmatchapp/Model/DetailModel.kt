package com.dicoding.myfootballmatchapp.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DetailModel (
        @SerializedName("idEvent")
        var eventId : String? = null,
        @SerializedName("dateEvent")
       var matchSchedule: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("strHomeFormation")
        val homeFormation: String? = null,

        @SerializedName("strAwayFormation")
        val awayFormation: String? = null,

        @SerializedName("strHomeGoalDetails")
        val homeGoalDetails: String? = null,

        @SerializedName("strAwayGoalDetails")
        val awayGoalDetails: String? = null,

        @SerializedName("strHomeRedCards")
        val homeRedCard: String? = null,

        @SerializedName("strAwayRedCards")
        val awayRedCard: String? = null,

        @SerializedName("strHomeYellowCards")
        val homeYellowCard: String? = null,

        @SerializedName("strAwayYellowCards")
        val awayYellowCard: String? = null
) : Parcelable
