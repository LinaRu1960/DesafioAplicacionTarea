package cl.desafiolatam.desafiodos.task

import androidx.room.*

@Entity(tableName = "tarea")
data class TaskUIDataHolder (
    @PrimaryKey (autoGenerate = true) var id:Long=0,
    var text:String
)

@Dao
interface TaskDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task:TaskUIDataHolder)
    @Update
    fun update(task:TaskUIDataHolder)

    @Query("SELECT * FROM TAREA")
    fun getTasks():List<TaskUIDataHolder>

}