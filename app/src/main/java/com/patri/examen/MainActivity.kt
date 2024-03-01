package com.patri.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.patri.examen.database.StudentDatabase
import com.patri.examen.database.entities.StudentEntity
import com.patri.examen.database.entities.toDatabase
import com.patri.examen.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StudentsAdapter
    private lateinit var room : StudentDatabase//Instancia objeto Room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Construyo le objeto room
        room = Room.databaseBuilder(this, StudentDatabase::class.java,"movies").build()
        //llenarBBDD()
        fillDatabase()
        initUI()
        initListeners()
    }

    private fun fillDatabase(){//Lleno base de datos

        val names: List<String> = listOf("Briseida", "Anacleto", "Macaria", "Uldarico", "Pascasia", "Inolfo",
            "Pancracio", "Espaminondo", "Gervasio", "Patrocinio", "Hermenegilda", "Crescencio", "Cristobalina",
            "Agapito", "Tesifonte",   "Petronila", "Torcuato", "Vitorio", "Isidra", "Sibilia", "Ambrosio",
            "Delfina", "Tiburcio", "Margarito",   "Filemón", "Crisóloga", "Casimiro", "Cananea", "Felipa", "Cojoncio" )

        val grades: List<Double> = listOf(5.2, 4.3, 9.8, 6.7, 7.8, 5.0, 8.9, 9.7, 10.0,
            2.3, 3.5, 6.4, 8.4, 9.2, 1.3, 4.9,   5.7, 6.2, 8.5, 9.9, 2.5, 4.6, 5.8, 9.7,
            6.8, 8.2, 8.9, 6.4, 4.0, 10.0)

        //Lista mutable de objetos Student
        val lista = mutableListOf<Student>()

        //Recorro la lista, hay 30
        //Añadimos objetos a la lista de objetos
        for(i in 0..29){
            lista.add(Student(names[i],grades[i]))
        }

        CoroutineScope(Dispatchers.IO).launch {
            //Mapeo
            val studentsList = lista.map{it.toDatabase()}

            room.getStudentDao().deleteAllStudentsList()//Borro
            room.getStudentDao().insertAll(studentsList)//Inserto

            //para que saque por pantalla al iniciar
            val myResponse: List<StudentEntity> = room.getStudentDao().getAllStudents()

            if (myResponse != null) {
                runOnUiThread {
                    adapter.updateList(myResponse)
                }
            }
        }
    }

    private fun initUI() {
        adapter = StudentsAdapter()
        binding.rvStudents.setHasFixedSize(true)
        binding.rvStudents.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)//vertical
        binding.rvStudents.adapter = adapter

    }

    private fun initListeners() {//Inicio botones
        binding.tvAprobados.setOnClickListener{devuelveAprobados()}
        binding.tvSuspensos.setOnClickListener{devuelveSuspensos()}
        binding.tvTodos.setOnClickListener{devuelveTodos()}

    }

    private fun devuelveTodos() {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: List<StudentEntity> = room.getStudentDao().getAllStudents()//DAO

            if (myResponse != null) {
                runOnUiThread {
                    adapter.updateList(myResponse)//Actualizo
                }
            }
        }
    }

    private fun devuelveSuspensos() {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: List<StudentEntity> = room.getStudentDao().getSuspenso()//DAO

            if (myResponse != null) {
                runOnUiThread {
                    adapter.updateList(myResponse)//Actualizo
                }
            }
        }
    }

    private fun devuelveAprobados() {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: List<StudentEntity> = room.getStudentDao().getAprobado()//DAO

            if (myResponse != null) {
                runOnUiThread {
                    adapter.updateList(myResponse)//Actualizo
                }
            }
        }
    }



}