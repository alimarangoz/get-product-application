package com.alimarangoz.product.services

import com.alimarangoz.product.models.ProductList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetProductService {

    @GET("/products")
    fun getProduct(@Query("limit") limit: Int): Call<ProductList>

}