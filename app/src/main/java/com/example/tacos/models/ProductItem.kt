package com.example.tacos.models

class ProductItem(
    val product : Product
) {
    val tags : String by lazy {
        product.recipe
            .lines()
            .firstOrNull {it.startsWith("tags:")}
            ?: "tags: none"
    }
    val ingredients : List<String> by lazy {
        product.recipe
            .lines()
            .filter {it.startsWith("* ")}
    }
}