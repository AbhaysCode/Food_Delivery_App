package com.example.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fooddelivery.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()

            val user = User(userName,phoneNumber,password)
            if (userName.isNotEmpty()&&phoneNumber.isNotEmpty()&&password.isNotEmpty()){
                databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                databaseReference.child(userName).setValue(user).addOnSuccessListener {
                    Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LogInActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Fill All the Fields",Toast.LENGTH_SHORT).show()
            }

        }
    }
}