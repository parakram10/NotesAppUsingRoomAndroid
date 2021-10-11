package com.example.notesappusingroomandroid.NotesDao

import androidx.room.*
import com.example.notesappusingroomandroid.data.Note

@Dao
interface NotesDAO {

    @Query("SELECT * FROM note")
    fun getAll() : List<Note>

    @Insert
    fun insert(note : Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note")
    fun deleteAll()

    @Query("SELECT * from note WHERE note_uid LIKE :nid LIMIT 1")
    fun findNoteById(nid : Int) : Note

    @Query("SELECT * from note WHERE note_title LIKE :title LIMIT 1")
    fun findNoteByTitle(title : String) : Note

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM note ORDER BY note_title")
    fun sortByTitle() : List<Note>
}