package com.alimarangoz.product.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.alimarangoz.product.R
import com.alimarangoz.product.models.ProductList
import com.bumptech.glide.Glide

class CustomAdapter(private val context: Context, private val products: ProductList) : BaseAdapter() {

    override fun getCount(): Int {
        return products.products.size
    }

    override fun getItem(position: Int): Any {
        return products.products[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        }

        val product = products.products[position]

        val productImage = view?.findViewById<ImageView>(R.id.product_image)
        if (productImage != null) {
            Glide.with(context).load(product.images[0]).into(productImage)
        }

        val productName = view?.findViewById<TextView>(R.id.product_name)
        productName?.text = product.title

        val productPrice = view?.findViewById<TextView>(R.id.product_price)
        productPrice?.text = product.price.toString()

        return view!!
    }
}