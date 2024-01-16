package com.example.familytodolist

import android.icu.text.CaseMap.Title

data class FamilyToDoListData (
    val complated : Boolean,
    val title: String,
    val dueDate : String
)