package com.example.familytodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.familytodolist.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        val curUser = auth.currentUser

        if (curUser != null){
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    fun goToRegisterTv(view: View) {
        var intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
    fun loginButton(view: View) {
        val email = binding.LoginEmailText.text.toString()
        val password = binding.LoginPasswordText.text.toString()

        if (email !="" && password !=""){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                if (task.isSuccessful){

                    //val userName = auth.currentUser?.displayName.toString()
                    val userEmail = auth.currentUser?.displayName.toString()
                    Toast.makeText(applicationContext,"Welcome ${email}", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener() {exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }
}