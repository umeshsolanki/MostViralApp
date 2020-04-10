package com.umesh.mostviral.network;

import android.util.Log
import com.google.gson.*
import com.umesh.mostviral.modals.Gallery
import com.umesh.mostviral.modals.Meme
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

object RetrofitApi {

        private const val TAG:String="RetrofitAPI"
        private const val base:String="https://api.imgur.com/"
        private const val apiKey:String="ee6237b1ea6b2bb"
        private const val apiSecret:String="f99f51b5b233ecf855062a70e673b06d566f9872"

        //must be in DB/SharedPref
        const val accessToken = "3a092e97437769ccf21620947da49d32dc4d1eab"
        const val refreshToken = "8b8791d224dcbe01add9ea2130018aa3d5fb1aff"

        private lateinit var retrofit:Retrofit

        fun getClient():Retrofit {
                Log.d(javaClass.simpleName,"Retrofit access")
                if (::retrofit.isInitialized) {
                        return retrofit
                }
                val client = OkHttpClient.Builder()
                        .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60,TimeUnit.SECONDS)
                        .connectTimeout(60,TimeUnit.SECONDS)
                        .addInterceptor(HttpLoggingInterceptor())
                        .addInterceptor { chain ->
                                val req = chain.request().newBuilder().addHeader("Authorization",
                                        "Client-ID $apiKey")
                                        .build()
                                chain.proceed(req)
                        }
                        .build()
                        var gson = GsonBuilder()
                                .registerTypeAdapter(Gallery::class.java,GalleryDeserializer())
                                .create()
                        retrofit = Retrofit.Builder().baseUrl(base).client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                return retrofit
        }

}
class GalleryDeserializer : JsonDeserializer<Gallery> {
        val TAG = "Deserializer"
        override fun deserialize(json: JsonElement?, typeOfT: Type?,
                                 context: JsonDeserializationContext?): Gallery {
                Log.d(TAG,"${json?.toString()}")
                return Gson().fromJson<Gallery>(json?.asJsonObject,typeOfT)
        }

}
