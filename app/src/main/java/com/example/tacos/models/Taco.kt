package com.example.tacos.models

import com.google.gson.annotations.SerializedName

data class Taco(
        val mixin: Product,
        val seasoning: Product,
        @SerializedName("base_layer")
        val baseLayer: Product,
        val condiment: Product,
        val shell: Product
) {
        fun getProducts() : List<Product> =
        listOf(
                baseLayer,
                mixin,
                condiment,
                seasoning,
                shell
        )
}