package com.example.familytodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.familytodolist.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }
    }
    fun signInButton(view: View)
    {
        val email = binding.registerEmailText.text.toString()
        val username = binding.usernameText.text.toString()
        val password = binding.passwordText.text.toString()
        val confirmPassword = binding.passwordAgainText.text.toString()

        if (password == confirmPassword) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val profileUpdateRequest = userProfileChangeRequest { displayName = username }

                    currentUser?.updateProfile(profileUpdateRequest)?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            Toast.makeText(applicationContext, "User is added.", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, ProfileActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Failed to update username", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext, "Your passwords do not match.", Toast.LENGTH_LONG).show()
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }
        val userName = binding.usernameText.text.toString()

        val intent = Intent(applicationContext,ProfileActivity::class.java)
        intent.putExtra("sendedUserName",userName)
        startActivity(intent)
    }
}