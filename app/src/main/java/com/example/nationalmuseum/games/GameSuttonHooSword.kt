package com.example.nationalmuseum.games

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import com.example.nationalmuseum.*
import okio.IOException
import retrofit2.HttpException




class GameSuttonHooSword : AppCompatActivity(), ArtefactClosed {
    val artefactId = 2
    val story = Game_SuttonHoo_Sword_Story(this)
    val user:User = User("test")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_sutton_hoo_sword)

        story.intro_1()
    }

    fun displayFragment(){
        var artifact: Artifact
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getArtefact(artefactId)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpExeception, unexpected responce")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                artifact = response.body()!!
                lifecycleScope.launchWhenCreated {
                    val response = try {
                        RetrofitInstance.api.patchArtifact(user.id, artifact)
                    } catch (e: IOException) {
                        Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                        return@launchWhenCreated
                    } catch (e: HttpException) {
                        Log.e(ContentValues.TAG, "HttpExeception, unexpected responce")
                        return@launchWhenCreated
                    }
                }
                supportFragmentManager.beginTransaction().apply {
                    bundle.putParcelable("artefact", artifact)
                    artifactFragment.arguments = bundle
                    replace(R.id.flFragment, artifactFragment)
                    commit()
                }
            } else {
                Log.e(ContentValues.TAG, "not successful")
            }
        }
    }

    fun createButtons(){
        val layout = findViewById<LinearLayout>(R.id.linearLayoutButtons)
        layout.removeAllViews()
        if(story.buttonNum > 0) {
            var button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button.text = story.buttonText1
            button.textSize = 20.0F
            button.setOnClickListener { story.selectPosition(story.answer1Result) }
            layout.addView(button)
        }

        if(story.buttonNum > 1) {
            var button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button.text = story.buttonText2
            button.textSize = 20.0F
            button.setOnClickListener { story.selectPosition(story.answer2Result) }
            layout.addView(button)
        }

        if(story.buttonNum > 2) {
            var button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button.text = story.buttonText3
            button.textSize = 20.0F
            button.setOnClickListener { story.selectPosition(story.answer3Result) }
            layout.addView(button)
        }

        if(story.buttonNum > 3) {
            var button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button.text = story.buttonText4
            button.textSize = 20.0F
            button.setOnClickListener { story.selectPosition(story.answer4Result) }
            layout.addView(button)
        }
    }

    override fun artefactClosed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}