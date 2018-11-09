package com.dicoding.myfootballmatchapp.Response

import android.os.Parcelable
import com.dicoding.myfootballmatchapp.Model.NextMatchModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class NextMatchResponse (
    @SerializedName("events")
    var data:List<NextMatchModel>

):Parcelable