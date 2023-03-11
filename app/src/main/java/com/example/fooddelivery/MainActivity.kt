package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myRecyclerView: RecyclerView
    lateinit var dishArrayList: ArrayList<dish>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myRecyclerView = binding.recyclerView
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        var imageArray = arrayOf(R.drawable.baapofrolls,R.drawable.cholebhature,R.drawable.fassos,R.drawable.standardburfee,R.drawable.baapofrolls,R.drawable.cholebhature,R.drawable.fassos,R.drawable.standardburfee)
        var dishName = arrayOf("Baap Of Rolls","Chole Bhature","Fassos","Standard Burfee","Baap Of Rolls","Chole Bhature","Fassos","Standard Burfee")
        dishArrayList = arrayListOf<dish>()
        for (index in imageArray.indices){
            val dishData = dish(dishName[index],imageArray[index])
            dishArrayList.add(dishData)
        }
        val myAdapter = DishAdapter(dishArrayList,this)
        myRecyclerView.adapter = myAdapter
        myAdapter.setOnItemClickListener(object:DishAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity,DishDetailActivity::class.java)
                intent.putExtra("dishImage",imageArray[position])
                intent.putExtra("dishName",dishName[position])
                startActivity(intent)
            }
        })
    }
}