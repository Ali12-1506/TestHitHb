package com.ali.loginapp.remote.apiRepository

import com.ali.loginapp.remote.dataModel.GetApiModel
import com.ali.loginapp.remote.dataModel.LoginModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApiService {

    @FormUrlEncoded
    @POST("email/login")
    suspend fun sendRequest(
        @Field("email") email: String,
    ):Response<LoginModel>


    @FormUrlEncoded
    @POST("email/login/verify")
    suspend fun verifyCode(
        @Header("app-device-uid") androidId : String,
        @Field("email") email: String,
        @Field("code") code:String
    ):Response<GetApiModel>
}