package com.alimarangoz.product.services

import com.alimarangoz.product.models.Data
import com.alimarangoz.product.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/auth/login")
    fun login( @Body user: User): Call<Data>
}