package com.patri.examen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.patri.examen.database.entities.StudentEntity
import com.patri.examen.databinding.ItemLayoutBinding


class StudentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding =  ItemLayoutBinding.bind(view)
    fun bind(studentItemResponse: StudentEntity) {

        binding.tvAlumno.text= studentItemResponse.name
        binding.tvNota.text = studentItemResponse.grade.toString()//Convierto a String

    }
}