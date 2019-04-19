package com.example.flags

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

private val COUNTRIES = listOf(
    "Israel",
    "Netherlands",
    "North Korea",
    "Romania",
    "Russia",
    "South Korea",
    "Spain",
    "Sweden",
    "Switzerland",
    "Syria",
    "Turkey",
    "Ukraine",
    "United States",
    "United Kingdom",
    "Uzbekistan",
    "Venezuela",
    "Vietnam",
    "Yemen"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (counry in COUNTRIES) {
            createFlag(counry)
        }
    }

    fun createFlag(countryName: String) {
        val flag = layoutInflater.inflate(R.layout.flag, null)
        val image = flag.findViewById<ImageButton>(R.id.flag_image)

        image.setOnClickListener {
             showCountryInfo(countryName)
        }

        val countryNameView = flag.findViewById<TextView>(R.id.country_name)
        val visitedBox = flag.findViewById<CheckBox>(R.id.visited_box)

        val countryName2 = countryName.toLowerCase().replace(" ", "_")
        val imageID = resources.getIdentifier(countryName2, "drawable", packageName)
        image.setImageResource(imageID)

        countryNameView.text = countryName
        grid_of_flags.addView(flag)
    }


    fun showCountryInfo(countryName: String) {
        val countryName2 = countryName.toLowerCase().replace(" ", "_")
        val textFileID = resources.getIdentifier(countryName2, "raw", packageName)
        val fileText = resources.openRawResource(textFileID).bufferedReader().readText()

        val mp3FieldId = resources.getIdentifier(countryName2 + "_anthem", "raw", packageName)
        val mp = MediaPlayer.create(this, mp3FieldId)
        mp.start()

        val bob = AlertDialog.Builder(this)
        bob.setTitle("Информация про $countryName")
        bob.setMessage(fileText)
        bob.setPositiveButton("OK") { _, _ ->
             mp.stop()
        }

        val dialog = bob.create()
        dialog.show()
    }
}
