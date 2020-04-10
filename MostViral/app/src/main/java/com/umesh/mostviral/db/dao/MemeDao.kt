package com.umesh.mostviral.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.umesh.mostviral.modals.Meme


@Dao
interface MemeDao{

    @Query("select * from Meme")
    fun getAll() : LiveData<List<Meme>>

    @Insert
    fun save(j:Meme)

    @Delete
    fun delete(j:Meme)

    @Query("delete from Meme where id =:id")
    fun delete(id:Long)

    @Query("select * from Meme where title like '%'||:input||'%'")
    fun search(input:String): LiveData<List<Meme>>


}