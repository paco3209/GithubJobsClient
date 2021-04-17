package com.androiddevs.githubjobsclient.api

import com.androiddevs.githubjobsclient.models.JobsItem
import com.androiddevs.githubjobsclient.models.JobsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JobsApi {

    @GET("/positions.json")
    suspend fun getJobs(): Response<JobsResponse>

    @GET("/positions.json")
    suspend fun searchJobs(
        @Query("description")
        description: String,
        @Query("page")
        page: Int
    ): Response<JobsResponse>


    @GET ("/positions/{id}.json")
    suspend fun getJob(
        @Path("id")id:String
    ): Response<JobsItem>


}