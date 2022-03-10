package fastcampus.aop.part2.chapter04.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fastcampus.aop.part2.chapter04.model.History

@Dao
interface HistoryDao{

    @Query("SELECT * FROM History")
    fun getAll(): List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM History")
    fun deleteAll()



    @Delete
    fun delete(history : History)

    @Query("SELECT * FROM History WHERE result LIKE :result LIMIT 1")
    fun findByResult(result : String) : History
}
