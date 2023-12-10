package ru.easycode.zerotoheroandroidtdd.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Url

interface SimpleService {

    @GET
    suspend fun fetch(@Url url: String): SimpleResponse

}

data class SimpleResponse(@SerializedName("text") val text: String)