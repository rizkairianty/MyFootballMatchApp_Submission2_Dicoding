package com.dicoding.myfootballmatchapp.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastMatchModel (
        @SerializedName("idEvent")
        var idEvent: String? = null,
        @SerializedName("strEvent")
        var eventName: String? = null,
        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,
        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,
        @SerializedName("dateEvent")
        var dateEvent: String? = null,
        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,
        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null
): Parcelable