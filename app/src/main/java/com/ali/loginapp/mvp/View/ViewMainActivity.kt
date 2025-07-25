package com.ali.loginapp.mvp.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.ali.loginapp.databinding.ActivityMainBinding
import com.ali.loginapp.remote.baseRetrofit.RetrofitService
import com.ali.loginapp.remote.dataModel.GetApiModel
import com.ali.loginapp.remote.ext.ActivityUtils
import com.ali.loginapp.remote.ext.ErrorHandler
import com.ali.loginapp.ui.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewMainActivity (private val context : Context, private val utils: ActivityUtils) {
    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    @SuppressLint("SetTextI18n")
    fun onClickHandler(androidId: String){
        binding.btnSend.setOnClickListener{
            val email = binding.edtInputEmail.text.toString().trim()
            if(email.isEmpty()){
                binding.textInputEmail.error = "Email is invalid !"
                return@setOnClickListener
            }else{
                binding.textInputEmail.error = null
            }
            sendCodeInEmail(email)

            binding.btnSend.visibility = View.INVISIBLE
            binding.textInputEmail.visibility = View.INVISIBLE

            binding.textInputCode.visibility = View.VISIBLE
            binding.btnConfirm.visibility = View.VISIBLE
            binding.txtWrong.visibility = View.VISIBLE
            binding.txtSendEmail.visibility = View.VISIBLE

            binding.txtSendEmail.text = "Code Sent to : ${email}"
        }

        binding.txtWrong.setOnClickListener {
            binding.btnSend.visibility = View.VISIBLE
            binding.textInputEmail.visibility = View.VISIBLE

            binding.textInputCode.visibility = View.INVISIBLE
            binding.btnConfirm.visibility = View.INVISIBLE
            binding.txtWrong.visibility = View.INVISIBLE
            binding.txtSendEmail.visibility = View.INVISIBLE
        }

        binding.btnConfirm.setOnClickListener {
            val code = binding.edtCode.text.toString()
            val mail = binding.edtInputEmail.text.toString().trim()
            if(code.isEmpty() || code.length != 6){
                binding.textInputCode.error = " "
            }else{
                binding.textInputCode.error = null
            }

            verifyCode(androidId,code,mail)
        }
    }

    private fun sendCodeInEmail(email:String) {
        val service = RetrofitService.apiService
        CoroutineScope(Dispatchers.IO).launch {
             try {
                 val response = service.sendRequest(email)
                 if(response.isSuccessful){
                     launch(Dispatchers.Main){}
                 }else{
                     launch(Dispatchers.Main){
                         Toast.makeText(context, ErrorHandler.getError(response), Toast.LENGTH_SHORT).show()
                     }
             }
            }catch (e:Exception){
                Log.i("SERVER_ERROR",e.message.toString())
            }
        }
    }


    private fun verifyCode(androidId: String,code: String, email: String) {
        val service = RetrofitService.apiService
        CoroutineScope(Dispatchers.IO).launch {
             try {
                 val response = service.verifyCode(androidId , email , code)
                 if(response.isSuccessful){
                     launch(Dispatchers.Main){
                         Toast.makeText(context, "Login successful! Let’s get started.", Toast.LENGTH_SHORT).show()
                         context.startActivity(Intent(context,HomeActivity::class.java))
                         utils.finishMvp()
                     }
                 }else{
                     launch(Dispatchers.Main){
                         Toast.makeText(context, ErrorHandler.getError(response), Toast.LENGTH_SHORT).show()
                     }
             }
            }catch (e:Exception){
                Log.i("SERVER_ERROR",e.message.toString())
            }
        }
    }
}