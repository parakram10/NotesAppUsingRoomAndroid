package com.example.notesappusingroomandroid.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class Note(text: String, desc: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_uid")
    private var uid = 0

    @ColumnInfo(name = "note_title")
    private var text: String = text

    @ColumnInfo(name = "note_desc")
    private var desc : String = desc

    fun getUid(): Int {
        return uid
    }

    fun setUid(uid: Int) {
        this.uid = uid
    }

    fun getText(): String {
        return text
    }

    fun getDesc(): String {
        return desc
    }

    override fun toString(): String {
        return ("Todo{uid= $uid, title= $text, desc= $desc}")
    }

}