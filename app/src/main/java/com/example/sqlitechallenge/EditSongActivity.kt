package com.example.sqlitechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sqlitechallenge.models.Song

class EditSongActivity : AppCompatActivity() {
    lateinit var updateSongBtn: Button
    lateinit var editTitleET: EditText
    lateinit var editArtistET: EditText
    lateinit var editAlbumET: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)
        val song_id = intent.getIntExtra("song_id", 0)
        val databaseHandler = SongsTableHandler(this)
        val song = databaseHandler.read()

        editTitleET = findViewById(R.id.editTitleET)
        editArtistET = findViewById(R.id.editArtistET)
        editAlbumET = findViewById(R.id.editAlbumET)
        updateSongBtn = findViewById(R.id.updateSongBtn)

        editTitleET.setText(song.title)
        editArtistET.setText(song.artist)
        editAlbumET.setText(song.album)

        updateSongBtn.setOnClickListener {

            val title = editTitleET.text.toString()
            val artist = editArtistET.text.toString()
            val album = editAlbumET.text.toString()
            val updated_song = Song(0, title = title, artist = artist, album = album)
            if (databaseHandler.update(Song)) {
                Toast.makeText(applicationContext, "Song was added", Toast.LENGTH_SHORT)
            } else {
                Toast.makeText(applicationContext, "Oops, something went wrong", Toast.LENGTH_SHORT)

            }
        }
    }
}