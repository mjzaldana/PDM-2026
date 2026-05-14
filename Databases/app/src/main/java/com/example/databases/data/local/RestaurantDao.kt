package com.example.databases.data.local

import androidx.room.*
import com.example.databases.data.local.entities.CategoryEntity
import com.example.databases.data.local.entities.CategoryWithDishes
import com.example.databases.data.local.entities.DishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: DishEntity)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Transaction
    @Query("SELECT * FROM categories")
    fun getCategoriesWithDishes(): Flow<List<CategoryWithDishes>>

    @Query("SELECT * FROM dishes WHERE categoryId = :categoryId")
    fun getDishesByCategory(categoryId: Long): Flow<List<DishEntity>>

    @Delete
    suspend fun deleteDish(dish: DishEntity)
}
