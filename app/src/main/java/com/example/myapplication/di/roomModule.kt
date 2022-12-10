package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.MainActivity
import com.example.myapplication.reposity.DataBase.mutiscreenDao
import com.example.myapplication.reposity.DataBase.mutiscreenDataBase
import com.example.myapplication.reposity.room.mutiscreenApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
object DataBaseModule{
    @Provides
    @Singleton
     fun providesDataBase(@ApplicationContext applicationContext: Context):mutiscreenDataBase{
        return Room.databaseBuilder(applicationContext,mutiscreenDataBase::class.java,"muti").build()
    }

    @Provides
    fun provideLogDao(dataBase: mutiscreenDataBase):mutiscreenDao{
        return dataBase.mutiscreendao()
    }
}