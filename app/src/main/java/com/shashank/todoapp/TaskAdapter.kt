package com.shashank.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shashank.todoapp.Entity.Task
import kotlinx.android.synthetic.main.single_todo.view.*

class TaskAdapter(context: Context) : RecyclerView.Adapter<TaskAdapter.viewHolder>() {

    val list:ArrayList<Task> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view =LayoutInflater.from(parent.context).inflate(R.layout.single_todo,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val current =list[position]
        holder.Vtitle.text=current.title
        holder.Vsubtitle.text=current.des
        holder.Vdate.text=current.date
        holder.Vtime.text=current.time

    }

    fun update(newTod:List<Task>){
        list.clear()
        list.addAll(newTod)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
     return list.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val Vtitle =itemView.titleTxt
        val Vsubtitle =itemView.subTitleTxt
        val Vdate =itemView.datatxt
        val Vtime = itemView.timeTxt

    }


}