package com.example.project.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageService {

    @GET("api/user/auction/{useridx}/{status}")
    suspend fun getAuction(
        @Path("useridx") useridx: String,
        @Path("status") status: String,
    ): Response<RegistResponse>


    @GET("api/user/barter/{useridx}/{status}")
    suspend fun getbarter(
        @Path("useridx") useridx: String,
        @Path("status") status: String,
    ): Response<RegistResponse>

    @GET("api/user/store/{useridx}/{status}")
    suspend fun getstore(
        @Path("useridx") useridx: String,
        @Path("status") status: String,
    ): Response<RegistResponse>

    @GET("api/user/mygift/{useridx}/{status}")
    suspend fun getmygift(
        @Path("useridx") useridx: String,
        @Path("status") status: String,
    ): Response<RegistResponse>


}