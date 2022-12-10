package com.example.myapplication.reposity.room

import androidx.annotation.WorkerThread
import com.example.myapplication.bean.mutiscreen
import com.example.myapplication.reposity.DataBase.mutiscreenDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class DiMutiscreenReposity @Inject constructor(private val mutiscreenDao: mutiscreenDao){

    @WorkerThread
    suspend fun length():Int{
        return mutiscreenDao.length()
    }

    @WorkerThread
    suspend fun insert(mutiscreen: mutiscreen) {
        return mutiscreenDao.insert(mutiscreen)
    }

    @WorkerThread
    suspend fun query():List<mutiscreen>{
        return mutiscreenDao.queryAll()
    }

    @WorkerThread
    suspend fun deleteByid(id:Int):Int{
        return mutiscreenDao.deleteById(id)
    }

    @WorkerThread
    suspend fun delete(mutiscreen: mutiscreen):Int{
        return mutiscreenDao.delete(mutiscreen)
    }

    @WorkerThread
    suspend fun update(mutiscreen: mutiscreen):Int{
        return mutiscreenDao.update(mutiscreen)
    }

}