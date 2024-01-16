package com.example.familytodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.familytodolist.databinding.ActivityProfileBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    val db = Firebase.firestore
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth

        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadMyInfo()
    }
    fun loadMyInfo(){
        val user = Firebase.auth.currentUser
        user?.let {
            val displayName = it.displayName
            val email = it.email

            // Update the TextViews with the user's data
            binding.nameTv.text = displayName
            binding.emailTv.text = email
        }
    }
    fun btnComposeToDoList(view: View) {
        Toast.makeText(this,"You are redirected to the to-do list item page", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,ToDoActivity::class.java)
        startActivity(intent)
    }
    fun btnLogout(view: View) {
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun btnDeleteAccount(view: View) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete Account")
        alertDialogBuilder.setMessage("Do you really want to delete your account? This action cannot be undone.")

        alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->

            AuthUI.getInstance().delete(this).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {

                    Toast.makeText(this, "Account deletion failed..", Toast.LENGTH_SHORT).show()
                }
            }
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun goToMyToDoList(view: View) {
        Toast.makeText(this,"You are redirected to the my to do list page",Toast.LENGTH_SHORT).show()
        val intent = Intent(this,ToDoActivity::class.java)
        startActivity(intent)
    }

    fun goToFamilyToDoList(view: View) {
        Toast.makeText(this,"You are redirected to the family to do list page",Toast.LENGTH_SHORT).show()
        val intent = Intent(this,FamilyToDoList::class.java)
        startActivity(intent)
    }

}