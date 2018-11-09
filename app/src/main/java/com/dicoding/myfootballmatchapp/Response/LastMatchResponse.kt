package com.dicoding.myfootballmatchapp.Response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import com.dicoding.myfootballmatchapp.Model.LastMatchModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class LastMatchResponse (
    @SerializedName("events")
var data:List<LastMatchModel>
):Parcelable