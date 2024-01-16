package com.example.familytodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(private val toDoList: List<ToDoListData>) : RecyclerView.Adapter<RecyclerAdapter.ToDoListVH>() {

    class ToDoListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerViewTextView: TextView = itemView.findViewById(R.id.recyclerViewTextView)
        val checkbox : CheckBox = itemView.findViewById(R.id.checkbox)
        val titleText : TextView = itemView.findViewById(R.id.titleTextView)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_each_task, parent, false)
        return ToDoListVH(itemView)
    }

    override fun onBindViewHolder(holder: ToDoListVH, position: Int) {
        val currentItem = toDoList[position]

        // currentItem'ı kullanarak RecyclerView öğelerini doldurun
        holder.recyclerViewTextView.text = currentItem.dueDate
        holder.titleText.text = currentItem.title
        holder.checkbox.isChecked = currentItem.complated


    }
}

