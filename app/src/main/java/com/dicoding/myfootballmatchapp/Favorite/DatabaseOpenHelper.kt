package com.dicoding.myfootballmatchapp.Favorite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper (ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase) {
        p0.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT + UNIQUE,
                Favorite.HOME_NAME to TEXT,
                Favorite.AWAY_NAME to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(Favorite.TABLE_FAVORITE,true)
    }

    companion object {
        private var instance : DatabaseOpenHelper? = null
        @Synchronized
        fun getInstance(ctx : Context) : DatabaseOpenHelper{
            if(instance == null){
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)