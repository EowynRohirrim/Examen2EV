package com.patri.examen.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.patri.examen.database.entities.StudentEntity

@Dao
interface StudentDao {

    //Búsqueda que devuelve la tabla entera
    @Query("SELECT * FROM students_table")
    suspend fun getAllStudents(): List<StudentEntity>


    //Búsqueda
    @Query("SELECT * FROM students_table WHERE name Like  :query")
    suspend fun getStudents(query: String): List<StudentEntity>

    //Búsqueda por id
    @Query("SELECT * FROM students_table Where id LIKE :id")
    suspend fun getAllStudentsID(id: Int): StudentEntity


    //Estudiantes aprobados
    @Query("SELECT * FROM students_table Where grade >=5.0")
    suspend fun getAprobado(): List<StudentEntity>

    //Estudiantes suspendidos
    @Query("SELECT * FROM students_table Where grade <5.0")
    suspend fun getSuspenso(): List<StudentEntity>


    /***Métodos para borrar e insertar se necesitan si o si*/

    //Para insertar se usa insert, insertar en la tabla
    @Insert(onConflict = OnConflictStrategy.REPLACE) //onConflict si hay error de los superheroes entonces reemplaza
    suspend fun insertAll(movies: List<StudentEntity>) //objetos de la base de datos

    //Delete se usa query, para borrar la BBDD
    @Query("DELETE FROM students_table")
    suspend fun deleteAllStudentsList()

}