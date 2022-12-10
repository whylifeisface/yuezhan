package com.example.myapplication

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class MainViewModel() : ViewModel() {


    private var _expanded: MutableLiveData<Boolean> = MutableLiveData(false)
    val expanded: LiveData<Boolean> = _expanded

    fun dropDownHidden() {
        this._expanded.value = true
    }

    val text = (0..100)
        .asSequence()
        .asFlow()
        .map {
            if (it < 10) "0$it" else it
        }.onEach {
            delay(1000)
        }

    fun dropDownClick(it: String): () -> Unit {
        return {
            when (it) {
                "编辑" -> {

                }
                "添加" -> {

                }
                "删除" -> {

                }
                "前往" -> {}
                "移动" -> {

                }
            }
        }
    }

    fun dropDownShow() {
        this._expanded.value = true

    }
}
//class WordViewModelFactory(private val repository: mu) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return WordViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
