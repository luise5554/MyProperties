package com.example.myproperties.domain.model

data class PropertyTypeModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val name: String,
)
