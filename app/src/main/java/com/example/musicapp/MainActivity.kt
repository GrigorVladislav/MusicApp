package com.example.musicapp

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.artists.view.*

class MainActivity : AppCompatActivity() {


    private val ARTISTS = listOf(
        "ac_dc",
        "bon_jovi",
        "disturbed",
        "godsmack",
        "guns_n_roses",
        "kipelov",
        "led_zeppelin",
        "metallica",
        "nightwish",
        "nirvana",
        "ozzy_osborn",
        "pink_floyd",
        "rammstein",
        "slipknot",
        "system_of_a_down"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (artist: String in ARTISTS) {
        createArtist(artist)

        }

    }
    fun createArtist(artistName:String) {

        val label: View = layoutInflater.inflate(R.layout.artists, null)
        val image:ImageView = label.artist_image
        image.setOnClickListener {
            showArtistInfo(artistName)
        }
        val artistNameView: TextView = label.name_artist
        val artistName2:String = artistName.toLowerCase().replace("","")
        val imageID :Int =resources.getIdentifier(artistName2,"drawable",packageName)
        image.setImageResource(imageID)
        artistNameView.text = artistName
        grid_of_artist.addView(label)

    }
    fun showArtistInfo(artistName: String){
        val artistName2:String = artistName.toLowerCase().replace("","")
        val textFileID :Int =resources.getIdentifier(artistName2,"raw",packageName)
        val fileText :String = resources.openRawResource(textFileID).bufferedReader().readText()
        val mp3FileID :Int =resources.getIdentifier(artistName2+"_anthem","raw",packageName)
        val mp = MediaPlayer.create(this, mp3FileID)
        mp.start()

val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Info about $artistName")
        builder.setMessage(fileText)
        builder.setPositiveButton("OK"){_,_->
        mp.stop()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

}
