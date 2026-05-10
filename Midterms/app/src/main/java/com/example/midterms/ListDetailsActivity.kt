package com.example.midterms

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ListDetailsActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details)

        val propertyAddress = intent.getStringExtra("propertyAddress")
        val price = intent.getStringExtra("price")
        val type = intent.getStringExtra("type")
        val squareFootage = intent.getStringExtra("squareFootage")
        val bedroomCount = intent.getStringExtra("bedroomCount")

        val propertyAddress2 = findViewById<TextView>(R.id.propertyAddress)
        val price2 = findViewById<TextView>(R.id.price)
        val type2 = findViewById<TextView>(R.id.type)
        val squareFootage2 = findViewById<TextView>(R.id.squareFootage)
        val bedroomCount2 = findViewById<TextView>(R.id.bedroomCount)

        propertyAddress2.text = ("Address: $propertyAddress")
        price2.text = ("Price: $price")
        type2.text = ("Type: $type")
        squareFootage2.text = ("Square Footage: $squareFootage")
        bedroomCount2.text = ("Bedroom Count: $bedroomCount")
    }
}