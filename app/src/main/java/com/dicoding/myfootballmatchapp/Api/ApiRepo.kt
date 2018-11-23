package com.dicoding.myfootballmatchapp.Api

import java.net.URL

class ApiRepo {
    fun doRequest(url : String) : String{
        return URL(url).readText()
    }
}