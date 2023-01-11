package com.example.nationalmuseum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ArtifactAdapter (var artifacts: List<Artifact>,
                       private val listener: OnItemClickListener): RecyclerView.Adapter <ArtifactAdapter.ArtifactViewHolder>() {

    inner class ArtifactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val itemName: TextView = itemView.findViewById(R.id.tvTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            val artefact = artifacts[position]

            if (position != RecyclerView.NO_POSITION) {
                listener.onClick(artefact)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(artefact: Artifact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtifactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artifact, parent, false)
        return ArtifactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtifactViewHolder, position: Int) {
        holder.itemName.text = artifacts[position].name
    }

    override fun getItemCount(): Int {
        return artifacts.size
    }


}