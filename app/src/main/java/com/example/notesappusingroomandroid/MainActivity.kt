package com.example.notesappusingroomandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappusingroomandroid.adapter.notesAdapter
import com.example.notesappusingroomandroid.data.Note


class MainActivity : AppCompatActivity() {

    val list : ArrayList<Note> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val title : EditText = findViewById(R.id.title)
        val desc : EditText = findViewById(R.id.description)
        val add : Button = findViewById(R.id.add_note)
        val sortById : Button = findViewById(R.id.sort_by_id)
        val sortByWord : Button = findViewById(R.id.sort_by_word)

        list.add(Note("acd", "dsfnefriejrfelrkfmioejrflemrf"))

        val notesAdapter = notesAdapter(this, list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = notesAdapter

        add.setOnClickListener(){
            val t = title.text.toString()
            val d = desc.text.toString()
            if(t.isNotEmpty() && d.isNotEmpty()){
                list.add(Note(t, d))
                notesAdapter.notifyDataSetChanged()
                title.setText("")
                desc.setText("")
            }
        }

        notesAdapter.notifyDataSetChanged()
    }
}