package com.dicoding.myfootballmatchapp.Response

import android.os.Parcelable
import com.dicoding.myfootballmatchapp.Model.TeamBadgeModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TeamBadgeResponse (
    @SerializedName("teams")
    var data : List<TeamBadgeModel>
) : Parcelable