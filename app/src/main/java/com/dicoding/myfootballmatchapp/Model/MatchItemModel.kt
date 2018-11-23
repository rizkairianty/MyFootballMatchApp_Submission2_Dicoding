package com.dicoding.myfootballmatchapp.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchItemModel (
        @SerializedName("idEvent")
        var idEvent: String? = "",
        @SerializedName("strEvent")
        var eventName: String? = "",
        @SerializedName("strHomeTeam")
        var homeTeam: String? = "",
        @SerializedName("strAwayTeam")
        var awayTeam: String? = "",
        @SerializedName("intHomeScore")
        var homeScore: String? = "",
        @SerializedName("intAwayScore")
        var awayScore: String? = "",
        @SerializedName("dateEvent")
        var dateEvent: String? = "",
        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = "",
        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = ""
): Parcelable