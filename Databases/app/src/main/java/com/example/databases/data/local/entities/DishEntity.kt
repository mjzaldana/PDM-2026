package com.example.databases.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dishes",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            //Se recomienda restrict en lugar de cascade
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId")]
    //indices = [Index(value = ["categoryId", "userId"])]
)
data class DishEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val price: Double,
    val categoryId: Long
)