package cl.desafiolatam.desafiodos

import android.content.DialogInterface
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.desafiodos.task.OnItemClickListener
import cl.desafiolatam.desafiodos.task.TaskListAdapter
import cl.desafiolatam.desafiodos.task.TaskUIDataHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_task.view.*

/*
[X] 1. Debe agregar todas las dependencias y configuraciones necesarias para integrar Room en el proyecto.
    [X] a. Agregar dependencias y configuraciones a el archivo gradle correspondiente
    [X] b. Crear las clases necesarias para inicializar la base de datos.
    [X] c. Agregar configuración al Manifest

[] 2. Crear todos los componentes de Room para persistir la lista y poder interactuar con los datos guardados.
    [] a. La lista sólo muestra texto, no sobre complique el modelamiento de datos. Recuerde implementar todos los componentes de Room, esto entrega.

[] 3. Completar los siguientes métodos y mostrar que la aplicación persiste los datos entre diferentes corridas de la misma.
    [] a. crear las variables en la clase MainActivity, inicializar dichas variables donde corresponde.
    [] b. completar el método para editar o actualizar una tarea, método updateEntity.
          completar el método para agregar una tarea, método addTask, utilizar estos métodos donde corresponda.
    [] c. completar los métodos: para convertir entre un entity y la clase que mapea la data en pantalla
          createEntityListFromDatabase. El método que crea una entidad a partir de texto, método
          createEntity. Completar el método que borra todas las tareas de la base de datos.
    [] d. La aplicación debe ejecutarse sin problemas y persistir los datos entre diferentes corridas de la misma.
 */


class MainActivity : AppCompatActivity(), OnItemClickListener {
    override fun onItemClick(taskItem: TaskUIDataHolder) {
        val dialogView = layoutInflater.inflate(R.layout.add_task, null)
        val taskText = dialogView.task_input
        taskText.setText(taskItem.text)
        val dialogBuilder = AlertDialog
            .Builder(this)
            .setTitle("Editar una Tarea")
            .setView(dialogView)
            .setNegativeButton("Cerrar") {
                    dialog: DialogInterface, _: Int -> dialog.dismiss()}
            .setPositiveButton("Editar") {
                    _: DialogInterface, _: Int ->
                //generar código para editar/actualizar la tarea
            }
        dialogBuilder.create().show()
    }

    private lateinit var list: RecyclerView
    private lateinit var adapter: TaskListAdapter
    // crear las variables para utilizar la base de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setUpViews()
        //inicializar lo necesario para usar la base de datos
    }

    override fun onResume() {
        super.onResume()
        AsyncTask.execute {
            val newItems = mutableListOf<TaskUIDataHolder>()
            runOnUiThread {
                adapter.updateData(newItems)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when {
            item.itemId == R.id.add -> addTask()
            item.itemId == R.id.remove_all -> removeAll()
        }
        return true
    }

    private fun setUpViews() {
        list = task_list
        list.layoutManager = LinearLayoutManager(this)
        adapter = TaskListAdapter( mutableListOf(), this, this)
        list.adapter = adapter
    }

    private fun updateEntity(taskItem: TaskUIDataHolder, newText: String) {
        //completar método para actualizar una tarea en la base de datos
    }

    private fun addTask() {
        val dialogView = layoutInflater.inflate(R.layout.add_task, null)
        val taskText = dialogView.task_input
        val dialogBuilder = AlertDialog
            .Builder(this)
            .setTitle("Agrega una Tarea")
            .setView(dialogView)
            .setNegativeButton("Cerrar") {
                    dialog: DialogInterface, _: Int -> dialog.dismiss()}
            .setPositiveButton("Agregar") {
                    dialog: DialogInterface, _: Int ->
                if (taskText.text?.isNotEmpty()!!) {
                    //Completar para agregar una tarea a la base de datos
                }
            }
        dialogBuilder.create().show()
    }

    private fun removeAll() {
        val dialog = AlertDialog
            .Builder(this)
            .setTitle("Borrar Todo")
            .setMessage("¿Desea Borrar todas las tareas?")
            .setNegativeButton("Cerrar") {
                    dialog: DialogInterface, _: Int -> dialog.dismiss()}
            .setPositiveButton("Aceptar") { dialog: DialogInterface, _: Int ->
                //Código para eliminar las tareas de la base de datos
            }
        dialog.show()
    }
    private fun createEntity(text:String) {
        //completar este método para retornar un Entity
    }

    private fun createEntityListFromDatabase(/* párametro de entrada*/): MutableList<TaskUIDataHolder> {
        val dataList = mutableListOf<TaskUIDataHolder>()
        //completar método para crear una lista de datos compatibles con el adaptador, mire lo que
        //retorna el método. Este método debe recibir un parámetro también.
        return dataList
    }
}
