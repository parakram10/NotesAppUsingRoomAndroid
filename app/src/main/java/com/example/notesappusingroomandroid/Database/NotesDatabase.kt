package com.example.notesappusingroomandroid.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesappusingroomandroid.NotesDao.NotesDAO
import com.example.notesappusingroomandroid.data.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDAO

    companion object{
        @Volatile
        private var INSTANCE : NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase?{
            if(INSTANCE == null){
                synchronized(NotesDatabase::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, "MY_DATABASE")
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}