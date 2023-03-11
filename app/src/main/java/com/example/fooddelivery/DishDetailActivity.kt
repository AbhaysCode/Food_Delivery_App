package com.example.fooddelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fooddelivery.databinding.ActivityDishDetailBinding

class DishDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDishDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDishDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ivDishImage = intent.getIntExtra("dishImage",R.drawable.baapofrolls)
        val tvDishName = intent.getStringExtra("dishName")
        binding.tvDishNameDetail.text = tvDishName
        binding.ivDish.setImageResource(ivDishImage)

        binding.btnBuyNow.setOnClickListener {
            Toast.makeText(this,"Order Placed !!",Toast.LENGTH_SHORT).show()
        }
    }
}