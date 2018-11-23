package com.dicoding.myfootballmatchapp.Api

import android.net.Uri
import com.dicoding.myfootballmatchapp.BuildConfig

object TheSportDBApi {
    fun getLastMatch(league: String? = "4335"): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", league)
                .build()
                .toString()
    }
    fun getNextMatch(league: String? = "4335"): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", league)
                .build()
                .toString()
    }

    fun getMatchDetail(matchDetail: String? = ""): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupevent.php")
                .appendQueryParameter("id", matchDetail)
                .build()
                .toString()
    }

    fun getBadgeUrl(teamBadge: String? = ""): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchteams.php")
                .appendQueryParameter("t", teamBadge)
                .build()
                .toString()
    }
}