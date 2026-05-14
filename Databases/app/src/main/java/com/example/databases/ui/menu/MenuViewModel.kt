package com.example.databases.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.databases.data.local.RestaurantDao
import com.example.databases.data.local.entities.CategoryEntity
import com.example.databases.data.local.entities.CategoryWithDishes
import com.example.databases.data.local.entities.DishEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MenuViewModel(private val dao: RestaurantDao) : ViewModel(){

    val categoriesWithDishes: StateFlow<List<CategoryWithDishes>> = dao.getCategoriesWithDishes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addCategory(name: String){
        viewModelScope.launch {
            dao.insertCategory(CategoryEntity(name = name))
        }
    }

    fun addDish(name: String, price: Double, categoryId: Long){
        viewModelScope.launch {
            dao.insertDish(DishEntity(
                name = name,
                price = price,
                categoryId = categoryId
            ))
        }
    }

    fun deleteDish(dish: DishEntity){
        viewModelScope.launch {
            dao.deleteDish(dish)
        }
    }
}

class MenuViewModelFactory(private val dao: RestaurantDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MenuViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
