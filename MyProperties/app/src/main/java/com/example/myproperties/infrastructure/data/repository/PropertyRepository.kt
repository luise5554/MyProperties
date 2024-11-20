package com.example.myproperties.infrastructure.data.repository

import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.infrastructure.data.dao.PropertyDao
import com.example.myproperties.infrastructure.data.entity.toData
import com.example.myproperties.infrastructure.data.entity.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PropertyRepository @Inject constructor(private val propertyDao: PropertyDao) {

    val properties: Flow<List<PropertyModel>> = propertyDao.getPropertyList().map {
        propertiesData -> propertiesData.map { propertyEntity -> propertyEntity.toDomain() }
    }

    suspend fun addProperty(propertyModel: PropertyModel){
        propertyDao.addProperty(propertyModel.toData())
    }

}