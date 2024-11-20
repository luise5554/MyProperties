package com.example.myproperties.infrastructure.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myproperties.infrastructure.data.entity.PropertyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Query("Select * from PropertyEntity")
    fun getPropertyList(): Flow<List<PropertyEntity>>

    @Insert
    suspend fun addProperty(property: PropertyEntity)

}