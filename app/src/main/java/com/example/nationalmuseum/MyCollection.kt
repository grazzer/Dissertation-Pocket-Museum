package com.example.nationalmuseum

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okio.IOException
import retrofit2.HttpException

val artifactFragment = ArtifactFragment()
val bundle = Bundle()
val user:User = User("test")


class MyCollection : AppCompatActivity(), ArtifactAdapter.OnItemClickListener, ArtefactClosed {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_collection)
        val backBtn = findViewById<Button>(R.id.btnBack)

        populateCollection()

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        populateCollection()

    }

    override fun onClick(artefact: Artifact) {
        supportFragmentManager.beginTransaction().apply {

            bundle.putParcelable("artefact", artefact)
            artifactFragment.arguments = bundle
            replace(R.id.flFragment, artifactFragment)
            commit()
        }
    }

    override fun artefactClosed() {

    }

    private fun populateCollection() {

        var artifactList : List<Artifact> = listOf()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getCollection(user.id)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpExeception, unexpected responce")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                artifactList = response.body()!!
                updateList(artifactList)

            } else {
                Log.e(ContentValues.TAG, "not successful")
            }
        }
    }

    private fun updateList(artifactList: List<Artifact>){
//        var len = artifactList.size
//        Toast.makeText(this@MyCollection, len.toString(), Toast.LENGTH_SHORT).show()

        val rvCollection: RecyclerView = this.findViewById(R.id.rvCollection)
        val adaptor = ArtifactAdapter(artifactList, this)
        rvCollection.adapter = adaptor
        rvCollection.layoutManager = GridLayoutManager(this,3)
    }
}

