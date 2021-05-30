package com.shashank.todoapp.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(val title:String,val des:String,val date:String,val time:String) {
   @PrimaryKey(autoGenerate = true)var id:Int?=null
}