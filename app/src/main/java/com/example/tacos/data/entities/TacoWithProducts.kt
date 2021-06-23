package com.example.tacos.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TacoWithProducts (
    @Embedded
    val taco: Taco,
    @Relation( parentColumn = "base_slug", entityColumn = "slug")
    val base: Product,
    @Relation( parentColumn = "mixin_slug", entityColumn = "slug")
    val mixin: Product,
    @Relation( parentColumn = "condiment_slug", entityColumn = "slug")
    val condiment: Product,
    @Relation( parentColumn = "seasoning_slug", entityColumn = "slug")
    val seasoning: Product,
    @Relation( parentColumn = "shell_slug", entityColumn = "slug")
    val shell: Product
)