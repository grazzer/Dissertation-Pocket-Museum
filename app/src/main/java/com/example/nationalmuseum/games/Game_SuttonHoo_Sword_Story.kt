package com.example.nationalmuseum.games

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import com.example.nationalmuseum.MainActivity
import com.example.nationalmuseum.R
import com.example.nationalmuseum.RetrofitInstance
import okio.IOException
import retrofit2.HttpException

class Game_SuttonHoo_Sword_Story(val gameScreen: GameSuttonHooSword) {
    var buttonNum = 0

    var buttonText1 = ""
    var buttonText2 = ""
    var buttonText3 = ""
    var buttonText4 = ""

    var answer1Result = ""
    var answer2Result = ""
    var answer3Result = ""
    var answer4Result = ""

    var end : Boolean = false
    var artefact : Boolean = false

    fun selectPosition (result: String){
        when(result){
            "intro_2" -> intro2()
            "Start" -> start()
            "Found Something" -> identify()
            "Wrong Identify" -> identifyWrong()
            "Question 1" -> question_1()
            "Wrong Q1" -> question_1Wrong()
            "Artefact" -> foundArtefact()
            "Excuivate"-> excuivate()
            "End" -> end()

        }
    }

    fun intro_1(){
        buttonNum = 1
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.found_it)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "Hello, welcome to the Sutton Hoo dig site. My name is Basil Brown and i " +
                "am the lead archaeologist. It’s fantastic to have you here."

        buttonText1 = "Next"

        answer1Result = "intro_2"
        gameScreen.createButtons()

    }

    private fun intro2(){
        buttonNum = 1
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.found_it)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "Looks like we have some burial mounds here, but let's see what we find. " +
                "We are about to open up the biggest one at Miss Pretty’s request, this here is her land. Grab some equipment and let's see what we can find."

        buttonText1 = "End"

        answer1Result = "Start"
        gameScreen.createButtons()
    }

    private fun start(){
        buttonNum = 2
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.digging)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "Did you find something?"
        buttonText1 = "Yes"
        buttonText2 = "No"


        answer1Result = "Found Something"
        answer2Result = "End"
        gameScreen.createButtons()
    }

    private fun identify(){
        buttonNum = 4
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.box)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "What do you suppose it is?"
        buttonText1 = "An Axe"
        buttonText2 = "A Sword"
        buttonText3 = "Some Bones"
        buttonText4 = "I Can't Tell"

        answer1Result = "Wrong Identify"
        answer2Result = "Question 1"
        answer3Result = "Wrong Identify"
        answer4Result = "Wrong Identify"
        gameScreen.createButtons()
    }

    private fun identifyWrong(){
        buttonNum = 4
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.found_it)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "I don't think so, try again. It has a long sharp edge by the looks of it."
        buttonText1 = "An Axe"
        buttonText2 = "A Sword"
        buttonText3 = "Some Bones"
        buttonText4 = "A Cup"

        answer1Result = "Wrong Identify"
        answer2Result = "Question 1"
        answer3Result = "Wrong Identify"
        answer4Result = "Wrong Identify"
        gameScreen.createButtons()
    }

    private fun question_1(){
        buttonNum = 4
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.box)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "Who do you think the sword could have belonged too?"
        buttonText1 = "King Ethelred the Unready"
        buttonText2 = "Alfred the Great"
        buttonText3 = "King Raedwald"
        buttonText4 = "Harald Hardrada"

        answer1Result = "Wrong Q1"
        answer2Result = "Wrong Q1"
        answer3Result = "Artefact"
        answer4Result = "Wrong Q1"
        gameScreen.createButtons()
    }

    private fun question_1Wrong(){
        buttonNum = 4
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.found_it)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "I think that’s unlikely. Who else do you think the sword could have belonged too?"
        buttonText1 = "King Ethelred the Unready"
        buttonText2 = "Alfred the Great"
        buttonText3 = "King Raedwald"
        buttonText4 = "Harald Hardrada"

        answer1Result = "Wrong Q1"
        answer2Result = "Wrong Q1"
        answer3Result = "Artefact"
        answer4Result = "Wrong Q1"
        gameScreen.createButtons()
    }

    private fun foundArtefact(){
        buttonNum = 1
        gameScreen.findViewById<ImageView>(R.id.ivGameImage).setImageResource(R.drawable.box)
        gameScreen.findViewById<TextView>(R.id.textViewQuestion).text = "I think that's most likely. Let’s excavate it, it’s in fantastic condition. " +
                "Congratulations. It will be an excellent addition to the museum. I will go tell Miss Pretty."

        buttonText1 = "Excavate"

        answer1Result = "Excuivate"
        gameScreen.createButtons()
    }

    fun end(){
        val intent = Intent(gameScreen, MainActivity::class.java)
        gameScreen.startActivity(intent)
        gameScreen.finish()
    }

    fun excuivate(){
        gameScreen.displayFragment()

    }
}
