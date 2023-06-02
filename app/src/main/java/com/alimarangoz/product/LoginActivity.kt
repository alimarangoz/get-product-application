package com.alimarangoz.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViewsService
import android.widget.Toast
import com.alimarangoz.product.configs.ApiClient
import com.alimarangoz.product.configs.Util
import com.alimarangoz.product.models.Data
import com.alimarangoz.product.models.User
import com.alimarangoz.product.services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var loginBtn : Button
    lateinit var userService: UserService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userService = ApiClient.getClient().create(UserService::class.java)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.login_btn)


        loginBtn.setOnClickListener(loginBtnOnClickListener)

    }

    val loginBtnOnClickListener = View.OnClickListener {

        if (username.text.toString() == ""){
            username.error = "Username is required!"
        }
        if (password.text.toString() == ""){
            password.error = "Password is required!"
        }

        val username = username.text.toString()
        val password = password.text.toString()
        val user = User(username,password)


        userService.login(user).enqueue(object : Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                val user = response.body()
                if (user != null) {
                    Util.user = user
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error Occured, try again!", Toast.LENGTH_LONG).show()
            }

        })

    }

}