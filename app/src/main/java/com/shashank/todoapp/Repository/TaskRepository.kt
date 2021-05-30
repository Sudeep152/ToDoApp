package com.shashank.todoapp.Repository

import androidx.lifecycle.LiveData
import com.shashank.todoapp.Dao.TaskDao
import com.shashank.todoapp.Entity.Task

class TaskRepository(private val dao: TaskDao) {

    val allTask:LiveData<List<Task>> =dao.getTask()


    suspend fun  insert(task: Task){
        dao.inset(task)
    }


}