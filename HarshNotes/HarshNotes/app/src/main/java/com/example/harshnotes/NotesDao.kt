package com.example.harshnotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * From note_table ORDER BY comparableDate desc")
    fun getAllNotes():LiveData<List<Note>>

    @Query("Update note_table SET title=:newTitle, text=:newText, comparableDate=:newcdate, displayableDate=:newddate WHERE title=:oldTitle")
    suspend fun update(oldTitle: String, newTitle: String, newText: String, newcdate: String, newddate: String)
}