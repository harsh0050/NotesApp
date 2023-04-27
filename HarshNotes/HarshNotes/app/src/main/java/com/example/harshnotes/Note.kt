package com.example.harshnotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(@PrimaryKey @ColumnInfo(name = "title") val title : String, @ColumnInfo(name = "text") val text: String, @ColumnInfo(name = "comparableDate")val comparableDate: String, @ColumnInfo(name="displayableDate") val displayableDate: String){
    var id: Int = 0
}