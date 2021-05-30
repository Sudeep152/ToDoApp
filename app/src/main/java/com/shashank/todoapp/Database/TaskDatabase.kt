package com.shashank.todoapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shashank.todoapp.Dao.TaskDao
import com.shashank.todoapp.Entity.Task
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Task::class),version = 1,exportSchema = false)
abstract class TaskDatabase:RoomDatabase()  {

    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null


        fun getDatabase(
            context: Context
        ): TaskDatabase {

                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
            }
        }


    }
}