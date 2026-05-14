package com.example.databases

import android.app.Application
import com.example.databases.data.local.AppDatabase

class RestaurantApp : Application(){
    val database by lazy { AppDatabase.getDatabase(this) }
}