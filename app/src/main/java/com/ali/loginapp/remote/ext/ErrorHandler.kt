package com.ali.loginapp.remote.ext

import com.ali.loginapp.remote.dataModel.ErrorModel
import com.google.gson.Gson
import retrofit2.Response

object ErrorHandler {

    fun getError(response: Response<*>):String{

        var error : String? = null
        val errorBody = response.errorBody()

        if(errorBody != null){
            error = Gson().fromJson(errorBody.string(), ErrorModel::class.java).message
        }

        return error ?: "Oops, there's a problem."
    }
}