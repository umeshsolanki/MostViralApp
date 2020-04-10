package com.umesh.mostviral

import android.app.Application
import android.content.Context
import android.util.Log
import com.umesh.mostviral.db.AppDatabase

class App : Application() {

    companion object{

        private var DB: AppDatabase?=null

        fun getDb(): AppDatabase {
            return DB as AppDatabase
        }

        fun getString(ctx:Context,key:String):String?{
            val pref = ctx.applicationContext.getSharedPreferences(Constants.API_S_PREFERENCE, Context.MODE_PRIVATE)
            return pref.getString(key,null)
        }

        fun setString(ctx:Context,key:String,value:String){
            val pref = ctx.applicationContext.getSharedPreferences(Constants.API_S_PREFERENCE, Context.MODE_PRIVATE)
            pref.edit().putString(key,value).apply()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(javaClass.simpleName,"DB Created")
        DB = AppDatabase.getInstance(this)
    }



}