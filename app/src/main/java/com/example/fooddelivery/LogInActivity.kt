package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fooddelivery.databinding.ActivityLogInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogIn.setOnClickListener {
            val userName = binding.etUserNameLogIn.text.toString()
            val password = binding.etPasswordLogIn.text.toString()
            if (userName.isNotEmpty()&&password.isNotEmpty()){
                readData(userName,password)
            }else{
                Toast.makeText(this,"Fill all the Fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnSignUp.setOnClickListener {
            val intent  = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun readData(userName: String,userPassword:String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userName).get().addOnSuccessListener {
            if(it.exists()){
                val password = it.child("password").value
                if(password == userPassword){
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("userName",userName)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"No Such User Found !! Please Sign Up",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"No Such User Found !! Please Sign Up",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Database Error !! Try Again Later",Toast.LENGTH_SHORT).show()
        }
    }
}