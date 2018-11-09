package com.dicoding.myfootballmatchapp.Api

import retrofit2.Call
import com.dicoding.myfootballmatchapp.Response.LastMatchResponse
import com.dicoding.myfootballmatchapp.Response.NextMatchResponse
import com.dicoding.myfootballmatchapp.Response.TeamBadgeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/{api_key}/eventspastleague.php")
    fun LastMatch(
            @Path("api_key") path: String?,
            @Query("id") parameter : String?
    ) : Call<LastMatchResponse>

    @GET("api/v1/json/{api_key}/eventsnextleague.php")
    fun NextMatch(
            @Path ("api_key") path: String?,
            @Query ("id") parameter: String?
    ) : Call<NextMatchResponse>

    @GET("api/v1/json/{api_key}/lookupteam.php")
    fun TeamBadge(
            @Path ("api_key") path: String?,
            @Query ("id") parameter: String?
    ) : Call<TeamBadgeResponse>
}