package com.example.notesappusingroomandroid.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappusingroomandroid.Database.NotesDatabase
import com.example.notesappusingroomandroid.MainActivity
import com.example.notesappusingroomandroid.R
import com.example.notesappusingroomandroid.data.Note

class notesAdapter(private val context: Context, private val note: MutableList<Note>) : RecyclerView.Adapter<notesAdapter.ViewHolder>() {


    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.note_title)
        val desc : TextView = itemView.findViewById(R.id.note_desc)
        val delete : ImageView = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.desc.text = note[position].getDesc()
        holder.title.text = note[position].getText()

        holder.delete.setOnClickListener{

            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
            Thread{
                    val n = NotesDatabase.getDatabase(context.applicationContext)
                            ?.notesDao()
                            ?.findNoteById(note[position].getUid())

                    if (n != null) {
                        note.removeAt(position)
                        NotesDatabase.getDatabase(context.applicationContext)
                                ?.notesDao()
                                ?.delete(n)
                    }
            }.start()

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return note.size
    }
}