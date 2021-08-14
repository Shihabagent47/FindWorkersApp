package com.example.workersapp.utils
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.workersapp.R
import java.io.IOException


class GlideLoader(val context: Context) {

    fun loadUserPicture(imageURI: String, imageView: ImageView) {
        try {

            Glide
                .with(context)
                .load(Uri.parse(imageURI.toString())) // URI of the image
                .centerCrop() // Scale type of the image.
                .placeholder(R.drawable.ic_user_placeholder) // A default place holder if image is failed to load.
                .into(imageView) // the view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun loadProfilePicture(imageURI: String) {
        try {

            Glide
                .with(context)
                .load(Uri.parse(imageURI.toString())).toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun loadProductPicture(image: Any, imageView: ImageView) {
        try {
            // Load the user image in the ImageView.
            Glide
                .with(context)
                .load(image) // Uri or URL of the image
                .centerCrop() // Scale type of the image.
                .into(imageView) // the view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

