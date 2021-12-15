package com.example.netclankotlin.explore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netclankotlin.R
import com.example.netclankotlin.explore.Data
import com.example.netclankotlin.utils.GlideHelper

class ExploreAdapter(val context: Context,val exploreList:ArrayList<Data>):RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.explore_layout,parent,false)
        return ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.textName.text = exploreList.get(position).firstName +" "+exploreList.get(position).lastName
        GlideHelper.setImageViewRoundedCorners(context,holder.profileImg,20,exploreList.get(position).profilePicUrl)
    }

    override fun getItemCount(): Int {
        return exploreList.size
    }


    inner class ExploreViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var profileImg : ImageView = itemView.findViewById(R.id.profileImg)
        var textName : TextView = itemView.findViewById(R.id.nameTxt)

    }


}