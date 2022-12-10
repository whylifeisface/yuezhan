package com.example.myapplication.reposity.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.bean.mutiscreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiMutiscreenViewModel @Inject  constructor(private val repository: DiMutiscreenReposity):ViewModel(){


    //协程异常处理 插入删除太快会崩溃
    fun insert() = viewModelScope.launch {
        val length =  repository.length()
        val mutiscreen = mutiscreen(length + 1)
        repository.insert(mutiscreen)
    }

    fun update(mutiscreen: mutiscreen) = viewModelScope.launch {

        repository.update(mutiscreen)
    }

    suspend fun query():List<mutiscreen> {
        return   repository.query()
    }

    fun deleteById(id:Int){
        viewModelScope.launch{
            delay(1000)
            val i = repository.deleteByid(id)
            if (i == 0){
                println("delete error")
                throw IllegalArgumentException("delete error")
            }
        }
    }

    fun delete(mutiscreen: mutiscreen){
        viewModelScope.launch{
            val i = repository.delete(mutiscreen)
            if (i == 0){
                throw IllegalArgumentException("delete error")
            }
        }
    }

}
