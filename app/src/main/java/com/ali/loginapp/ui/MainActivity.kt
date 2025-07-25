package com.ali.loginapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ali.loginapp.mvp.Model.ModelMainActivity
import com.ali.loginapp.mvp.Presenter.PresenterMainActivity
import com.ali.loginapp.mvp.View.ViewMainActivity
import com.ali.loginapp.remote.ext.ActivityUtils


class MainActivity : AppCompatActivity() , ActivityUtils {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this,this)
        setContentView(view.binding.root)


        val presenter = PresenterMainActivity(view , ModelMainActivity(this))
        presenter.onCreatePresenter()


    }

    override fun finishMvp() {
        finish()
    }
}