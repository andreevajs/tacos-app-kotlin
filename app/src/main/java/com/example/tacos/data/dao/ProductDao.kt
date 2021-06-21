package com.example.tacos.data.dao

import androidx.room.*
import com.example.tacos.data.entities.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM Product WHERE type = :type")
    fun getAllOfType(type : String): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Delete
    fun delete(user: Product)
}
