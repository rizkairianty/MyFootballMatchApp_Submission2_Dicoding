package com.dicoding.myfootballmatchapp.Response

import android.os.Parcelable
import com.dicoding.myfootballmatchapp.Model.DetailModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DetailResponse (
        @SerializedName("events")
    val events : List<DetailModel>
) : Parcelable