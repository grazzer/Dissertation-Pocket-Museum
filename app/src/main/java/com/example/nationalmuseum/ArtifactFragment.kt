package com.example.nationalmuseum

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope

import okio.IOException
import retrofit2.HttpException


class ArtifactFragment : Fragment(R.layout.fragment_artifact) {

    private lateinit var artifactClosed: ArtefactClosed

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artifact, container, false)

        val name = view.findViewById<TextView>(R.id.tvArtifactFragName)
        val discription = view.findViewById<TextView>(R.id.tvArtifactFragDisc)
        val culture = view.findViewById<TextView>(R.id.tvCulture)
        val age = view.findViewById<TextView>(R.id.tvAge)
        val findLoc = view.findViewById<TextView>(R.id.tvFindLoc)
        val image = view.findViewById<ImageView>(R.id.ivArtefactImage)

        val data = arguments
        val artefact = data!!.getParcelable<Artifact>("artefact")
        name.text = artefact?.name
        culture.text = "Culture:    " + artefact?.Cultures
        age.text =     "Creation date:      " +artefact?.productionDate
        findLoc.text = "Excavation location:    " +artefact?.findCountry
        discription.text = artefact?.discription

        when (artefact?.name) {
            "The Sutton Hoo Sword" -> image.setImageResource(R.drawable.sutton_hoo_sword_image)
            "The Sutton Hoo Helmet" -> image.setImageResource(R.drawable.sutton_hoo_helmet_image)
            else -> {
                image.setImageResource(R.drawable.box)
            }
        }

        artifactClosed = activity as ArtefactClosed

        val btnClose = view.findViewById<Button>(R.id.btnClose)
        btnClose.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
            artifactClosed.artefactClosed()
        }
        return view
    }
}


