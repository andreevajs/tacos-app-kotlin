package com.example.tacos.models

enum class ProductType(val value: String) {
    MIXIN("mixins"),
    BASE_LAYER("base_layers"),
    CONDIMENT("condiments"),
    SEASONING("seasonings"),
    SHELL("shells")
}