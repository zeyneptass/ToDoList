package com.example.familytodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FamilyRecyclerAdapter(private val familyToDoList: List<FamilyToDoListData>) : RecyclerView.Adapter<FamilyRecyclerAdapter.ToDoListVH>() {
    class ToDoListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText : TextView = itemView.findViewById(R.id.titleTextView)
        val checkBox : CheckBox = itemView.findViewById(R.id.checkbox)
        val editTextDate : TextView = itemView.findViewById(R.id.editTextDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_family_to_do_list, parent, false)
        return ToDoListVH(itemView)
    }

    override fun onBindViewHolder(holder: ToDoListVH, position: Int) {
       val currentItem = familyToDoList[position]

       holder.titleText.text = currentItem.title
       holder.editTextDate.text = currentItem.dueDate
       holder.checkBox.isChecked = currentItem.complated

    }

    override fun getItemCount(): Int {
        return familyToDoList.size
    }
}

