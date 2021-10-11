package com.example.notesappusingroomandroid

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappusingroomandroid.Database.NotesDatabase
import com.example.notesappusingroomandroid.adapter.notesAdapter
import com.example.notesappusingroomandroid.data.Note
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {

    private var list : MutableList<Note> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val title : EditText = findViewById(R.id.title)
        val desc : EditText = findViewById(R.id.description)
        val add : Button = findViewById(R.id.add_note)
        val sortById : Button = findViewById(R.id.sort_by_id)
        val sortByWord : Button = findViewById(R.id.sort_by_word)

        getAllNotes(recyclerView)

        add.setOnClickListener(){
            val t = title.text.toString()
            val d = desc.text.toString()
            if(t.isNotEmpty() && d.isNotEmpty()){
                insertSingleNote(t,d,recyclerView)
                title.setText("")
                desc.setText("")
            }
            getAllNotes(recyclerView)
        }

        sortById.setOnClickListener{
            NotesDatabase.getDatabase(applicationContext)
                    ?.notesDao()
                    ?.deleteAll()

            getAllNotes(recyclerView)
        }

        sortByWord.setOnClickListener{
            list = NotesDatabase.getDatabase(applicationContext)
                    ?.notesDao()
                    ?.sortByTitle()!!

            val notesAdapter = notesAdapter(this, list)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = notesAdapter
            notesAdapter.notifyDataSetChanged()
        }
    }

    private fun getAllNotes(recyclerView : RecyclerView) {

        list = NotesDatabase.getDatabase(applicationContext)
                ?.notesDao()
                ?.getAll()!!

        val notesAdapter = notesAdapter(this, list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = notesAdapter
        notesAdapter.notifyDataSetChanged()
    }

    private fun insertSingleNote(title: String, desc : String, recyclerView: RecyclerView){
        val note : Note = Note(title, desc)
        val insertAsyncTask : InsertAsyncTask = InsertAsyncTask(this@MainActivity)
        insertAsyncTask.execute(note)
        getAllNotes(recyclerView)
    }

    class InsertAsyncTask(activity: MainActivity) : AsyncTask<Note, Void, Void>(){

        private val activityReference : WeakReference<MainActivity> = WeakReference(activity)

        override fun doInBackground(vararg params: Note): Void? {
            activityReference.get()?.let {
                NotesDatabase.getDatabase(it.applicationContext)
                        ?.notesDao()
                        ?.insert(params[0])
            }
            return null
        }

    }
}