package com.example.harshnotes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var title : TextView = itemView.findViewById(R.id.title)
    var text: TextView = itemView.findViewById(R.id.text)
    var dateModified: TextView = itemView.findViewById(R.id.date)
}
class Adapter(private val listener: CustomListener): RecyclerView.Adapter<ViewHolder>() {
    private var notes : List<Note> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val viewHolder = ViewHolder(itemView)
        itemView.setOnClickListener{
            listener.onClicked(viewHolder)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = notes[position].text
        holder.title.text = notes[position].title
        holder.dateModified.text = notes[position].displayableDate

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNotes(newNotes : List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}

interface CustomListener {
    fun onClicked(viewHolder: ViewHolder)
}
