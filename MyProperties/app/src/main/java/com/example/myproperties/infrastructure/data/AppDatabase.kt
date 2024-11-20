package com.example.myproperties.infrastructure.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myproperties.infrastructure.data.dao.PhotoDao
import com.example.myproperties.infrastructure.data.dao.PropertyDao
import com.example.myproperties.infrastructure.data.entity.PhotoEntity
import com.example.myproperties.infrastructure.data.entity.PropertyEntity

@Database(entities = [PropertyEntity::class, PhotoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
    abstract fun photoDao(): PhotoDao
}