package com.dicoding.myfootballmatchapp.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TeamBadgeModel (
    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
): Parcelable