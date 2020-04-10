package com.umesh.mostviral.modals

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class  Meme(@PrimaryKey val id:String,
                 @SerializedName("link")var img:String,
                 var title:String,
                 var type:String,
                 var dateTime: Date,
                 @SerializedName("score")var points:Int)