package com.umesh.mostviral.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umesh.mostviral.App
import com.umesh.mostviral.db.dao.MemeDao
import com.umesh.mostviral.modals.Meme
import java.io.Serializable

@Database(entities = [Meme::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(),Serializable{

    abstract fun getMemesDao() : MemeDao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getInstance(ctx:Context): AppDatabase {
            if (INSTANCE !=null)
                return INSTANCE as AppDatabase

            synchronized(AppDatabase::class){
            if (INSTANCE ==null) {
                INSTANCE = Room.databaseBuilder(ctx, AppDatabase::class.java, "app.db")
                    .allowMainThreadQueries().build()
                Log.d(javaClass.simpleName, "DB initialized")
            }
            }
            return INSTANCE as AppDatabase
        }


    }

}