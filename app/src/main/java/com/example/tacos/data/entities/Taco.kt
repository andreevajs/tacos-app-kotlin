package com.example.tacos.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["base_slug","mixin_slug","condiment_slug","seasoning_slug","shell_slug"])
class Taco (
    @ColumnInfo(name = "base_slug") val base: String,
    @ColumnInfo(name = "mixin_slug") val mixin: String,
    @ColumnInfo(name = "condiment_slug") val condiment: String,
    @ColumnInfo(name = "seasoning_slug") val seasoning: String,
    @ColumnInfo(name = "shell_slug") val shell: String
)