package com.patri.examen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patri.examen.database.entities.StudentEntity


class StudentsAdapter(var studentsList: List<StudentEntity> = emptyList()) : RecyclerView.Adapter<StudentsViewHolder>() {
    //He cambiado de String a int

    fun updateList(list: List<StudentEntity>) {//Ahora es una lista de List<Entity>
        studentsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        return StudentsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }
    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.bind(studentsList[position])
    }
    override fun getItemCount() = studentsList.size
}