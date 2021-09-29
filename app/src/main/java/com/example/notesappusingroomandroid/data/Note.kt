package com.example.notesappusingroomandroid.data

class Note(private var title: String, private var desc: String) {

    fun getTitle() : String{
        return title
    }

    fun getDesc() : String{
        return desc
    }

    fun setTitle(t : String) {
        title = t
    }

    fun setDesc(d : String){
        desc = d
    }
}