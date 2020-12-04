package com.example.sqlitechallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sqlitechallenge.models.Song

class CreateSongActivity : AppCompatActivity() {
    lateinit var addSongBtn: Button
    lateinit var titleET: EditText
    lateinit var artistET: EditText
    lateinit var albumET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_song)
        val databaseHandler = SongsTableHandler(this)
        titleET = findViewById(R.id.titleET)
        artistET = findViewById(R.id.artistET)
        albumET = findViewById(R.id.albumET)

        addSongBtn = findViewById(R.id.addSongBtn)
        addSongBtn.setOnClickListener{
            val title = titleET.text.toString()
            val artist = artistET.text.toString()
            val album = albumET.text.toString()
            val song = Song (title = title, artist = artist, album = album)
            if(databaseHandler.create(song)) {
                Toast.makeText(applicationContext, "Song was added", Toast.LENGTH_SHORT)
            } else {
                Toast.makeText(applicationContext, "Oops, something went wrong", Toast.LENGTH_SHORT)
            }
            clearFields()
        }
    }
    fun clearFields() {
        titleET.text.clear()
        artistET.text.clear()
        albumET.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.display_songs -> {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}