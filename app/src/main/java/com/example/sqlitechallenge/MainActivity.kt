package com.example.sqlitechallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.sqlitechallenge.models.Song

class MainActivity : AppCompatActivity() {
    lateinit var songsListView: ListView
    lateinit var songsTableHandler: SongsTableHandler
    lateinit var songs: MutableList<Song>
    lateinit var adapter: ArrayAdapter<Song>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        songsListView = findViewById(R.id.songsListView)
        songsTableHandler = SongsTableHandler(this)
        songs = songsTableHandler.read()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songs)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.create_song -> {
                startActivity(Intent(applicationContext, CreateSongActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.song_item_menu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId){
            R.id.edit_song -> {
                val song_id = songs[info.position].id
                val intent = Intent(applicationContext, EditSongActivity::class.java)
                intent.putExtra("song_id", song_id)
                startActivity(intent)
                true
            }
            R.id.delete_song -> {
                true
            }
            else -> super.onContextItemSelected(item)
        }

    }
}