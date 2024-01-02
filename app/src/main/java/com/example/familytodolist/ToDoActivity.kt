package com.example.familytodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

class ToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        fun addToDoItemClicked(view: View) {
            showAddToDoDialog()
        }
    }
    private fun showAddToDoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Yeni To Do Ekle")

        builder.setPositiveButton("Tamam") { _, _ ->
            // To Do List'e yeni bir öğe ekleme işlemi burada gerçekleştirilir.
        }

        builder.setNegativeButton("İptal") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}