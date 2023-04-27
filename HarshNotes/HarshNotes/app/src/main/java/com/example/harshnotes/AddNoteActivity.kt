package com.example.harshnotes

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.harshnotes.databinding.ActivityAddNoteBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val isEditMode: Boolean =  intent.getBooleanExtra("isEditMode",false)
        if(isEditMode){
            //populate
            binding.textInput.setText(intent.getStringExtra("text"))
            binding.titleInput.setText(intent.getStringExtra("title"))

        }
        binding.saveBtn.setOnClickListener {
            val intentData = Intent()
            intentData.putExtra("title", binding.titleInput.editableText.toString())
            intentData.putExtra("text", binding.textInput.editableText.toString())
            val currentTime = LocalDateTime.now()
            intentData.putExtra("cdate",currentTime.toString())
            intentData.putExtra("ddate", DateTimeFormatter.ofPattern("MMMM dd, yyyy").format(currentTime))
            if(isEditMode){
                intentData.putExtra("oldTitle",intent.getStringExtra("title"))
            }
            setResult(Activity.RESULT_OK, intentData)
            finish()
        }
    }
}

