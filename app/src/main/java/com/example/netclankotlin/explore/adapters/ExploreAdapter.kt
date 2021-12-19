package com.example.netclankotlin.explore.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.netclankotlin.R
import com.example.netclankotlin.explore.activities.ThirdActivity
import com.example.netclankotlin.room.Note
import com.example.netclankotlin.utils.GlideHelper

class ExploreAdapter(
    val context: Context,
    val exploreList: ArrayList<Note>,
    val listener: Callback
) : RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.explore_layout, parent, false)
        return ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.textName.text =
            exploreList.get(position).Fname + " " + exploreList.get(position).Lname
        GlideHelper.setImageViewRoundedCorners(
            context,
            holder.profileImg,
            20,
            exploreList.get(position).profileIMG
        )
    }

    override fun getItemCount(): Int {
        return exploreList.size
    }


    inner class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var profileImg: ImageView = itemView.findViewById(R.id.profileImg)
        var textName: TextView = itemView.findViewById(R.id.nameTxt)

        init {

            textName.setOnClickListener { context.startActivity(Intent(context,ThirdActivity::class.java)) }
        }



    }

    interface Callback {
        fun getPosition(name: String)
    }
}