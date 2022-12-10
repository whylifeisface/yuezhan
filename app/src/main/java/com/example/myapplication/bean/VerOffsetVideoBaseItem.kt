package com.example.myapplication.bean

data class VerOffsetVideoBaseItem(
    val text:String,
    val totalTime:String,
    val comeAndComment:String,
    val iconButton:()->Unit
)

fun init():List<VerOffsetVideoBaseItem>{
    return listOf(
        VerOffsetVideoBaseItem("1","9.21","a"){},
        VerOffsetVideoBaseItem("2","17.21","a"){},
        VerOffsetVideoBaseItem("3","16.21","a"){},
        VerOffsetVideoBaseItem("4","15.21","r"){},
        VerOffsetVideoBaseItem("5","13.21","e"){},
        VerOffsetVideoBaseItem("6","9.21","b"){},
        VerOffsetVideoBaseItem("7","10.21","c"){},
        VerOffsetVideoBaseItem("8","11.21","d"){},

    )
}