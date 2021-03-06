package com.example.dolares.data.remote

import com.example.dolares.data.local.model.Capsule
import com.example.dolares.data.local.model.Core
import com.example.dolares.data.local.model.launch.Launch
import retrofit2.Response
import retrofit2.http.GET

interface SpacexApiService {

    @GET("capsules")
    suspend fun getAllCapsules(): Response<List<Capsule>>

    @GET("cores")
    suspend fun getAllCores(): Response<List<Core>>

    @GET("launches")
    suspend fun getAllLaunches(): Response<MutableList<Launch>>

//    @GET("launches")
//    suspend fun getSpecificLaunch(@Query("id")launchId:String):Response<Launch>

}