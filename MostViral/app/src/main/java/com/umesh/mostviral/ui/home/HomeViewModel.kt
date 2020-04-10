package com.umesh.mostviral.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umesh.mostviral.modals.Gallery
import com.umesh.mostviral.modals.Meme
import com.umesh.mostviral.network.GalleryApi
import com.umesh.mostviral.network.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val TAG = "HomeViewModel"


    val memes: MutableLiveData<MutableList<Meme>> by lazy {
        var liveData = MutableLiveData<MutableList<Meme>>()
            fetch(1)
        return@lazy liveData
    }

    fun fetch(page:Int) {
//        Log.d(TAG,"Load page $page")
        RetrofitApi.getClient()
            .create(GalleryApi::class.java)
            .getViralMemes("top", "viral", page,RetrofitApi.accessToken)
            .enqueue(object : Callback<Gallery>{
                override fun onFailure(call: Call<Gallery>, t: Throwable) {
                    t.printStackTrace()
                    Log.d(TAG,"Gallery Request Err")
                    Log.d(TAG,t.toString())
                }

                override fun onResponse(call: Call<Gallery>, response: Response<Gallery>) {
                    if (response.isSuccessful) {
                        val newMemes = response.body()?.memes
//                        Log.d(TAG,"Downloaded Memes : ${newMemes?.size}")
                        if (memes.value != null){
                            val memez = memes.value
                            if (newMemes != null) {
                                memez?.addAll(newMemes!!)
                                memes.value = memez
                            }
                        }else{
                            memes.value = newMemes
                        }
                    }
                }
            })
    }
}