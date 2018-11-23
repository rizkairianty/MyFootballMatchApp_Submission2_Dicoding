package com.dicoding.myfootballmatchapp.Response

import android.os.Parcelable
import com.dicoding.myfootballmatchapp.Model.MatchItemModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
internal class MatchItemResponse(
        @SerializedName("events")
        val events: List<MatchItemModel>
) : Parcelable