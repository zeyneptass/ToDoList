package com.example.familytodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.FirebaseFirestore

class FamilyToDoList : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private var toDoList: MutableList<ToDoListData> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_to_do_list)
        val addToDoListItem = findViewById<ShapeableImageView>(R.id.addToDoListItem)

        addToDoListItem.setOnClickListener {
            showAddToDoDialog()
        }
        recyclerView = findViewById(R.id.todoRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val db = FirebaseFirestore.getInstance()

        // Koleksiyon referansını alın
        val myCollectionRef = db.collection("CommonToDoList")

        myCollectionRef.get()
            .addOnSuccessListener { documents ->
                toDoList = mutableListOf()  // Her çağrıda liste sıfırlanmalıdır

                for (document in documents) {
                    val complated = document.getBoolean ("complated") ?: false
                    val dueDate = document.getString("dueDate") ?: ""
                    val title = document.getString("title") ?: ""

                    toDoList.add(ToDoListData(complated, dueDate,title))
                }

                // RecyclerView adapter'ını oluşturun ve bağlayın
                adapter = RecyclerAdapter(toDoList)
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                // Hata durumunda işlemler
            }
    }
    private fun showAddToDoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Yeni To Do Ekle")

        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.activity_each_task, null)
        builder.setView(dialogView)

        builder.setPositiveButton("Tamam") { _, _ ->
            // Dialog içindeki öğeleri al
            val titleTextView: EditText = dialogView.findViewById(R.id.titleTextView)
            val dueTextView: EditText = dialogView.findViewById(R.id.recyclerViewTextView)
            val checkbox: CheckBox = dialogView.findViewById(R.id.checkbox)

            // Kullanıcının girdiği verileri al
            val title = titleTextView.text.toString()
            val dueDate = dueTextView.text.toString()
            val isCompleted = checkbox.isChecked

            // Firestore'a yeni görevi ekle
            val newItem = hashMapOf(
                "title" to title,
                "dueDate" to dueDate,
                "complated" to isCompleted
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("CommonToDoList")
                .add(newItem)
                .addOnSuccessListener { documentReference ->
                    // Yeni görev eklendiğinde yapılacak işlemler
                }
                .addOnFailureListener { e ->
                    // Hata durumunda yapılacak işlemler
                }
        }

        builder.setNegativeButton("İptal") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}