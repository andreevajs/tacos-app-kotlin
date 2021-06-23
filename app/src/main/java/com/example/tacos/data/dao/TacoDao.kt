package com.example.tacos.data.dao

import androidx.room.*
import com.example.tacos.data.entities.Taco
import com.example.tacos.data.entities.TacoWithProducts

@Dao
interface TacoDao {
    @Transaction
    @Query("SELECT * FROM Taco")
    fun getAll(): List<TacoWithProducts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taco: Taco)

    @Delete
    fun delete(taco: Taco)
}
