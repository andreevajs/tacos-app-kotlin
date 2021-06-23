package com.example.tacos

import com.example.tacos.models.Product
import com.example.tacos.models.ProductItem
import org.junit.Test

import org.junit.Assert.*

class ProductItemUnitTests {
    @Test
    fun parse_ingredients() {
        val product = Product("","* tomato\n* soup","","")
        val item = ProductItem(product)
        assertEquals("the ingredients weren't parsed",2, item.ingredients.size)
    }

    @Test
    fun parse_ingredients_only_md_list() {
        val product = Product("","* tomato\n**ingo**","","")
        val item = ProductItem(product)
        assertEquals("extra line was parsed as ingredient",1, item.ingredients.size)
    }

    @Test
    fun parse_tags() {
        val product = Product("","ad\ntags: test\n*nsef","","")
        val item = ProductItem(product)
        assertEquals("the tags weren't parsed","tags: test", item.tags)
    }
}