package com.example.tacos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tacos.data.dao.ProductDao
import com.example.tacos.data.entities.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
