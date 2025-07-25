package com.ali.loginapp.androidWrapper

import android.content.Context
import android.provider.Settings

object DeviceInfo {

    var androidId:String ? = null

    fun getAndroidId(context: Context):String{
        if (androidId == null){
            androidId = Settings.Secure.getString(context.contentResolver,Settings.Secure.ANDROID_ID)
        }
        return androidId ?: ""
    }
}