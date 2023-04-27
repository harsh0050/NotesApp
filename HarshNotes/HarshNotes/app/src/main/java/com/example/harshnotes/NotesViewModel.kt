package com.example.harshnotes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(): ViewModel() {
    lateinit var repo: NotesRepo
    lateinit var allNotes: LiveData<List<Note>>
    fun insert(note: Note) = viewModelScope.launch{
        repo.insert(note)
    }
    fun delete(note: Note)= viewModelScope.launch {
        repo.delete(note)
    }
    fun update(oldTitle: String, newTitle: String, newText: String, newcdate: String, newddate: String)=viewModelScope.launch{
        repo.update(oldTitle, newTitle, newText, newcdate, newddate)
    }
    fun setAllNotes(context: Context): LiveData<List<Note>>{
        repo =  NotesRepo(NotesDatabase.getDatabase(context).getNotesDao())
        allNotes = repo.getAllNotes()
        return allNotes
    }

}