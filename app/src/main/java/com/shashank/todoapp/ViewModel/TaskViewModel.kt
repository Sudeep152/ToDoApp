package com.shashank.todoapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shashank.todoapp.Database.TaskDatabase
import com.shashank.todoapp.Entity.Task
import com.shashank.todoapp.Repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) :AndroidViewModel(application) {
    val repository :TaskRepository
    val allTask :LiveData<List<Task>>

    init {
        val dao = TaskDatabase.getDatabase(application).taskDao()
        repository= TaskRepository(dao)
        allTask = repository.allTask
    }

    fun insertNode(task: Task) =viewModelScope.launch ( Dispatchers.IO){
        repository.insert(task)
    }
}