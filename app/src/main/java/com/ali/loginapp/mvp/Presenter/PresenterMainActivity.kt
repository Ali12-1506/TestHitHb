package com.ali.loginapp.mvp.Presenter

import com.ali.loginapp.mvp.Model.ModelMainActivity
import com.ali.loginapp.mvp.View.ViewMainActivity

class PresenterMainActivity(private val view : ViewMainActivity , private val model : ModelMainActivity){

    fun onCreatePresenter(){
        view.onClickHandler(model.getAndroidId())
    }
}