package com.example.databases.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithDishes(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val dishes: List<DishEntity>
)