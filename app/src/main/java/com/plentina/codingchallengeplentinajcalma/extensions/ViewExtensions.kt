package com.plentina.codingchallengeplentinajcalma.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

/** This function loads the image from glide with normal center crop image
 *  this also places the placeholder when Glide encounters an error*/
fun ImageView.loadItemImage(uri: String?, drawable: Drawable){
    Glide.with(this.context)
        .load(uri)
        .dontAnimate()
        .fitCenter()
        .error(drawable)
        .into(this)
}

/** This function loads the image from glide with circle crop image*/
fun ImageView.loadItemImageCircleCrop(uri: String?, drawable: Drawable){
    Glide.with(this.context)
        .load(uri)
        .dontAnimate()
        .fitCenter()
        .circleCrop()
        .error(drawable)
        .into(this)
}