package com.shashank.todoapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.shashank.todoapp.Entity.Task
import com.shashank.todoapp.ViewModel.TaskViewModel
import kotlinx.android.synthetic.main.activity_task.*
import java.time.LocalDateTime

class TaskActivity : AppCompatActivity() {

    private var selectedHour: Int? = null
    private var selectedMinute: Int? = null

    lateinit var viewM :TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        supportActionBar?.title="Add Task"


        viewM = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)




        dateEdt.setOnClickListener {
            setDate()
        }
        timeEdt.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                showTimePicker()
            }
        }



        button.setOnClickListener {
            submitData()

        }

    }

    private fun submitData() {

        if (TextUtils.isEmpty(titleEdt.text.toString()) ||TextUtils.isEmpty(titleEdt.text.toString())  ||TextUtils.isEmpty(timeEdt.text.toString()) ||TextUtils.isEmpty(dateEdt.text.toString())){
            Toast.makeText(this, "Enter all Field", Toast.LENGTH_SHORT).show()

        }else{
            viewM.insertNode(Task(titleEdt.text.toString(),subtitleEdt.text.toString(),dateEdt.text.toString(),timeEdt.text.toString()))
            finish()
        }


    }

    private fun setDate() {
        val bilder = MaterialDatePicker.Builder.datePicker()

        bilder.setTitleText("Select a Date")
        val materialDatePicker = bilder.build()
        materialDatePicker.show(supportFragmentManager, "DATE_PI")


        materialDatePicker.addOnPositiveButtonClickListener { it ->
            dateEdt.setText(materialDatePicker.headerText)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showTimePicker(){
        val hour = selectedHour ?: LocalDateTime.now().hour
        val minute = selectedMinute ?: LocalDateTime.now().minute

        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(hour)
            .setMinute(minute)
            .build()
            .apply {
                addOnPositiveButtonClickListener { onTimeSelected(this.hour, this.minute) }
            }.show(supportFragmentManager, MaterialTimePicker::class.java.canonicalName)
    }

    private fun onTimeSelected(hour: Int, minute: Int) {
        selectedHour = hour
        selectedMinute = minute
        val hourAsText = if (hour < 10) "0$hour" else hour
        val minuteAsText = if (minute < 10) "0$minute" else minute


        timeEdt.setText("$hourAsText:$minuteAsText")
    }

}