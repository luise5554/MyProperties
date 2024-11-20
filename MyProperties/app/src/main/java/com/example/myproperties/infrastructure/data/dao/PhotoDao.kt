package com.example.myproperties.infrastructure.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myproperties.infrastructure.data.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("Select * from PhotoEntity where propertyId = :propertyId")
    fun getPhotos(propertyId: Int): Flow<List<PhotoEntity>>

    @Insert
    suspend fun addPhoto(photoEntity: PhotoEntity)

}