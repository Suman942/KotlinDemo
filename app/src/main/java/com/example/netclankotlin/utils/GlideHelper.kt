package com.example.netclankotlin.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class GlideHelper {
    companion object{
        fun setImageViewRoundedCorners(
            context: Context?,
            view: ImageView?,
            roundCorner: Int,
            url: String?
        ) {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(roundCorner))
            Glide.with(context!!)
                .load(url)
                .apply(requestOptions)
                .into(view!!)
        }
    }
}