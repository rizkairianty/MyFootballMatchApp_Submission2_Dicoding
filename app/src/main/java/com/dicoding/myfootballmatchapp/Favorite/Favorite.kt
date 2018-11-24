package com.dicoding.myfootballmatchapp.Favorite

class Favorite(val id : Long?, val eventId : String?, val homeName : String?, val awayName : String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID : String = "ID_"
        const val EVENT_ID : String = "EVENT_ID"
        const val HOME_NAME : String = "HOME_NAME"
        const val AWAY_NAME : String = "AWAY_NAME"
    }
}