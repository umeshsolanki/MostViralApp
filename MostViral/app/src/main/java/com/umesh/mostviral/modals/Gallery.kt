package com.umesh.mostviral.modals

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

class  Gallery {

    @SerializedName("data")
    var memes: MutableList<Meme> = mutableListOf()

}