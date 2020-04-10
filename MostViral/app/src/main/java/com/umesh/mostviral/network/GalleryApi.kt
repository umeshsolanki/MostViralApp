package com.umesh.mostviral.network

import com.umesh.mostviral.modals.Gallery
import com.umesh.mostviral.modals.Meme
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface GalleryApi {

    @GET("/3/gallery/{category}/{sort}/all/{page}?showViral=true&mature=false")
    fun getViralMemes(@Path("category") category:String,
                      @Path("sort") sort:String,
//                      @Path("window") win:String,
                      @Path("page") page:Int,
                      @Query("access_token") token:String
        ):Call<Gallery>

}