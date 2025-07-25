package com.ali.loginapp.mvp.Model

import android.content.Context
import com.ali.loginapp.androidWrapper.DeviceInfo
import kotlin.random.Random

class ModelMainActivity(private val context: Context) {
    fun getAndroidId() = DeviceInfo.getAndroidId(context) + Random.nextInt(0,1000).toString()
}