package com.example.`AlleyChaggar_COMP304_Hands-On_Test2_W23`

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.alleychaggar_comp304lab5.R

class LandmarkAdapter(
    private val landmarks: List<LandmarkModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_landmark, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val landmark = landmarks[position]
        holder.bind(landmark)

        // Handle item clicks
        holder.itemView.setOnClickListener { listener.onItemClick(position) }
    }

    override fun getItemCount(): Int = landmarks.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(landmark: LandmarkModel) {
            // Bind data to the ViewHolder views
        }
    }
}

data class LandmarkModel(val name: String, val latitude: Double, val longitude: Double) : Parcelable
