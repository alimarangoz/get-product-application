package com.alimarangoz.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.alimarangoz.product.adapters.CustomAdapter
import com.alimarangoz.product.configs.ApiClient
import com.alimarangoz.product.models.Product
import com.alimarangoz.product.models.ProductList
import com.alimarangoz.product.services.GetProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedProduct = customAdapter.getItem(position) as Product
            val list = arrayListOf<String>()
            list.add(selectedProduct.category)
            list.add(selectedProduct.brand)
            list.add(selectedProduct.price.toString())
            list.add(selectedProduct.description)
            list.add(selectedProduct.stock.toString())
            list.add(selectedProduct.images[0])

            val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
            intent.putExtra("selectedProduct", list)
            startActivity(intent)
        }

        val productService = ApiClient.getClient().create(GetProductService::class.java)

        productService.getProduct(10).enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                val productList = response.body()
                if (productList != null) {
                    customAdapter = CustomAdapter(this@MainActivity, productList)
                    listView.adapter = customAdapter
                }

            }


            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.e("fail",t.toString())
                Toast.makeText(this@MainActivity, "Error Occured, try again!", Toast.LENGTH_LONG).show()
            }
        })

    }

}

