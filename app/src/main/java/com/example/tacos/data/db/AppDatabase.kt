package com.example.tacos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tacos.data.dao.ProductDao
import com.example.tacos.data.dao.TacoDao
import com.example.tacos.data.entities.Product
import com.example.tacos.data.entities.Taco

@Database(entities = [Product::class,Taco::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun tacoDao(): TacoDao
}
