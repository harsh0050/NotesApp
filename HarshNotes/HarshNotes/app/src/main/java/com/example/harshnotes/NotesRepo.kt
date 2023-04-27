package com.example.harshnotes

import androidx.lifecycle.LiveData

class NotesRepo(private val dao: NotesDao) {
    suspend fun insert(note: Note){
        dao.insert(note)
    }
    suspend fun delete(note: Note){
        dao.delete(note)
    }
    fun getAllNotes(): LiveData<List<Note>>{
        return dao.getAllNotes()
    }
    suspend fun update(oldTitle: String, newTitle: String, newText: String, newcdate: String, newddate: String){
        dao.update(oldTitle, newTitle, newText, newcdate, newddate)
    }
}