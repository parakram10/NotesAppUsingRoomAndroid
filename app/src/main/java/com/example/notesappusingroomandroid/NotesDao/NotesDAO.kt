package com.example.notesappusingroomandroid.NotesDao

import androidx.room.*
import com.example.notesappusingroomandroid.data.Note

@Dao
interface NotesDAO {

    @Query("SELECT * FROM note")
    fun getAll() : MutableList<Note>

    @Insert
    fun insert(note : Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note")
    fun deleteAll()

    @Query("SELECT * from note WHERE note_title LIKE :title LIMIT 1")
    fun findNoteByTitle(title : String) : Note

    @Query("SELECT * from note WHERE note_uid LIKE :uid LIMIT 1")
    fun findNoteById(uid : Int) : Note

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM note ORDER BY note_title")
    fun sortByTitle() : MutableList<Note>
}