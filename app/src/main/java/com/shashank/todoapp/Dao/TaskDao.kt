package com.shashank.todoapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shashank.todoapp.Entity.Task
@Dao
interface TaskDao {


    @Query("SELECT * FROM Task ")
    fun getTask():LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inset(task: Task)
}