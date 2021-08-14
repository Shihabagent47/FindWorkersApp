package com.example.workersapp.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A data model class for Product with required fields.
 */
@Parcelize
data class Workers(
    val user_id: String = "",
    val user_name: String = "",
    val title: String = "",
    val price: String = "",
    val description: String = "",
    val image: String = "",
    var catagory: String = "",
    var profileImage: String = "",
    var longatude: String = "",
    var litatude: String = "",
    var product_id: String = ""

) : Parcelable