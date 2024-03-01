package com.patri.examen.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.patri.examen.Student


/**                             Tabla del Recycler View                  **/
@Entity(tableName="students_table")
data class StudentEntity(// Es como crear una tabla en SQL

    //Clave primaria
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0, //el primer registro que mete es cero (estilo lista)
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name  = "grade") val grade: Double
)


//Mapeo
fun Student.toDatabase() = StudentEntity(name = name, grade = grade)