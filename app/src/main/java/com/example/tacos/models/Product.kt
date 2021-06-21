package com.example.tacos.models

data class Product(
        val name: String,
        val recipe: String,
        val slug: String,
        val url: String
) {
    companion object {
        val TYPE_MIXIN ="mixins"
        val TYPE_CONDIMENT ="condiments"
        val TYPE_BASE_LAYER = "base_layers"
        val TYPE_BASE_SEASONINGS = "seasonings"
        val TYPE_BASE_SHELLS = "shells"
    }
}