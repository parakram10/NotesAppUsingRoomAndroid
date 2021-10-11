package com.example.notesappusingroomandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappusingroomandroid.R
import com.example.notesappusingroomandroid.data.Note

class notesAdapter(private val context: Context, private val note: List<Note>) : RecyclerView.Adapter<notesAdapter.ViewHolder>() {

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.note_title)
        val desc : TextView = itemView.findViewById(R.id.note_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.desc.text = note[position].getDesc()
        holder.title.text = note[position].getText()
    }

    override fun getItemCount(): Int {
        return note.size
    }
}