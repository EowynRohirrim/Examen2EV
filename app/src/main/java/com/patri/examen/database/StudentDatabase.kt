package com.patri.examen.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patri.examen.database.dao.StudentDao
import com.patri.examen.database.entities.StudentEntity


//Es un array con todas las tablas, con tantas clases entities como tablas tenga
@Database(entities = [StudentEntity::class], version = 1) //El 1 es un control de versiones, que ahora no se va a usar
abstract class StudentDatabase: RoomDatabase() {

    //Hay dos entities pues dos funciones, una por cada tabla
    abstract fun getStudentDao(): StudentDao

    //son getter porque es para coger información de la tabla de datos

}