package com.dicoding.myfootballmatchapp.Api

import com.dicoding.myfootballmatchapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    fun getClient() : Retrofit{
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInstance() : ApiInterface{
        return getClient().create(ApiInterface::class.java)
    }
}