package com.shashank.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shashank.todoapp.Entity.Task
import com.shashank.todoapp.ViewModel.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewMmodel:TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager=LinearLayoutManager(this)
        val  adapter=TaskAdapter(this)
        rv.adapter=adapter

        viewMmodel =ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)
        viewMmodel.allTask.observe(this, Observer {

            adapter.update(it)

        })

//
//        button.setOnClickListener {
//            submitData()
//        }
        addBtn.setOnClickListener {

            startActivity(Intent(this,TaskActivity::class.java))
        }


    }

}