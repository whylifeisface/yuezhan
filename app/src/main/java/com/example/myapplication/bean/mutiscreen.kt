package com.example.myapplication.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    主键
    第几位
    内容
 */
@Entity
class mutiscreen(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var text:String = "主页",
    @ColumnInfo(name = "is_active") var isActive:Boolean = false
)