package com.example.myproperties.infrastructure.di

import android.content.Context
import androidx.room.Room
import com.example.myproperties.infrastructure.data.AppDatabase
import com.example.myproperties.infrastructure.data.dao.PhotoDao
import com.example.myproperties.infrastructure.data.dao.PropertyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePropertyDatabase(@ApplicationContext applicationContext: Context): AppDatabase{
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, name= "AppDatabase").build()
    }

    @Provides
    fun providePropertyDao(appDatabase: AppDatabase): PropertyDao{
        return appDatabase.propertyDao()
    }

    @Provides
    fun providePhotoDao(appDatabase: AppDatabase): PhotoDao{
        return appDatabase.photoDao()
    }

}