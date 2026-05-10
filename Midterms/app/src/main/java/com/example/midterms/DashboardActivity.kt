package com.example.midterms

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DashboardActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard)

        val username = intent.getStringExtra("username");
        val loginText = findViewById<TextView>(R.id.loginText)

        val propertyAddress = findViewById<EditText>(R.id.propertyAddress)
        val price = findViewById<EditText>(R.id.price)
        val type = findViewById<EditText>(R.id.type)
        val squareFootage = findViewById<EditText>(R.id.squareFootage)
        val bedroomCount = findViewById<EditText>(R.id.bedroomCount)


        loginText.text = "Welcome, $username"


        val buttonSave = findViewById<Button>(R.id.buttonSave);

        buttonSave.setOnClickListener {
            val propertyAddress = propertyAddress.text.toString()
            val price = price.text.toString()
            val type = type.text.toString()
            val squareFootage = squareFootage.text.toString()
            val bedroomCount = bedroomCount.text.toString()

            if(!propertyAddress.isNullOrEmpty() && !price.isNullOrEmpty() && !type.isNullOrEmpty() && !squareFootage.isNullOrEmpty() && !bedroomCount.isNullOrEmpty()) {
                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ListDetailsActivity::class.java)
                intent.putExtra("propertyAddress", propertyAddress)
                intent.putExtra("price", price)
                intent.putExtra("type", type)
                intent.putExtra("squareFootage", squareFootage)
                intent.putExtra("bedroomCount", bedroomCount)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incomplete Input!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}