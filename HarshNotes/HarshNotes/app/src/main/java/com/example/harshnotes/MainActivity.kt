package com.example.harshnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harshnotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CustomListener {

    private val viewModel: NotesViewModel = NotesViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //boiler plate
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //*******************************************************

        //Recycler View
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this)
        binding.recyclerView.adapter = adapter
        //*******************************************************

        //View Model
        viewModel.setAllNotes(this)
        viewModel.allNotes.observe(this) {
            adapter.updateNotes(it)
        }
        //*******************************************************

        //Add Note Action
        val addIntentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode==RESULT_OK){
                val title : String? = result.data?.getStringExtra("title")
                val text :String?= result.data?.getStringExtra("text")
                val cdate :String? = result.data?.getStringExtra("cdate")
                val ddate: String? = result.data?.getStringExtra("ddate")

                if(title!=null && text!=null && ddate!=null&& cdate!=null){
                    val note = Note(title, text, cdate,ddate)
                    viewModel.insert(note)
                }
            }
        }
        binding.floatingActionButton.setOnClickListener{
            val addIntent = Intent(this, AddNoteActivity::class.java)
            addIntent.putExtra("isEditMode",false)
            addIntentLauncher.launch(addIntent)
        }
        //*******************************************************
    }


    //Notes On Click Listener(Edit/See Note)
    private val editIntentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode==RESULT_OK){
            val title : String? = result.data?.getStringExtra("title")
            val text :String?= result.data?.getStringExtra("text")
            val cDate :String? = result.data?.getStringExtra("cdate")
            val dDate: String? = result.data?.getStringExtra("ddate")
            val oldTitle : String? = result.data?.getStringExtra("oldTitle")

            if(title!=null && text!=null && dDate!=null&& cDate!=null && oldTitle!=null){
                viewModel.update(oldTitle,title,text,cDate,dDate)
            }
        }
    }
    override fun onClicked(viewHolder: ViewHolder) {
        val eIntent = Intent(this, AddNoteActivity::class.java)
        eIntent.putExtra("isEditMode",true)
        eIntent.putExtra("title",viewHolder.title.text)
        eIntent.putExtra("text",viewHolder.text.text)
        eIntent.putExtra("date",viewHolder.dateModified.text)
        editIntentLauncher.launch(eIntent)
    }
}