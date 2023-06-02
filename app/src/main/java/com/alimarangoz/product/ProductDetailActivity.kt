package com.alimarangoz.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class ProductDetailActivity : AppCompatActivity() {

    lateinit var image : ImageView
    lateinit var description : TextView
    lateinit var category : TextView
    lateinit var name : TextView
    lateinit var price : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val product : ArrayList<String> = intent.getSerializableExtra("selectedProduct") as ArrayList<String>

        image = findViewById(R.id.product_image)
        description = findViewById(R.id.product_description)
        category = findViewById(R.id.product_category)
        name = findViewById(R.id.product_brand)
        price = findViewById(R.id.product_price)

        category.setText("Category: " + product[0])
        name.setText("Name: " + product[1])
        price.setText("Price: " + product[2])
        description.setText("Description " + product[3])
        Glide.with(this)
            .load(product[5])
            .into(image);
    }
}