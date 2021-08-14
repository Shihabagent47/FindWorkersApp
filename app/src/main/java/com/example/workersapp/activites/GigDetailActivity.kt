package com.example.workersapp.activites


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.example.workersapp.R
import com.example.workersapp.firestore.FirestoreClass
import com.example.workersapp.models.User
import com.example.workersapp.models.Workers
import com.example.workersapp.utils.Constants
import com.example.workersapp.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_gig_detail.*
import kotlinx.android.synthetic.main.activity_gig_detail.iv_user_photo
import kotlinx.android.synthetic.main.activity_profile2.*

class GigDetailActivity : CardActivity() {

    private var mProductId: String = ""
    lateinit var phoneNumber:String

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_gig_detail)

        if (intent.hasExtra(Constants.EXTRA_PRODUCT_ID)) {
            mProductId =
                intent.getStringExtra(Constants.EXTRA_PRODUCT_ID)!!
            Log.i("Product Id", mProductId)
        }



        btn_call.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + phoneNumber )
            startActivity(dialIntent)
        }


        getProductDetails()
    }

    /**
     * A function for actionBar Setup.
     */
//    private fun setupActionBar() {
//
//        setSupportActionBar(toolbar_product_details_activity)
//
//        val actionBar = supportActionBar
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true)
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
//        }
//
//        toolbar_product_details_activity.setNavigationOnClickListener { onBackPressed() }
//    }



    /**
     * A function to receive the user details and populate it in the UI.
     */


    private fun getProductDetails() {

        // Show the product dialog
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of FirestoreClass to get the product details.
        FirestoreClass().getProductDetails(this@GigDetailActivity, mProductId)


    }



    fun productDetailsSuccess(product: Workers) {

        // Hide Progress dialog.
        hideProgressDialog()

        // Populate the product details in the UI.
        GlideLoader(this@GigDetailActivity).loadProductPicture(
            product.image,
            iv_product_detail_image
        )

        GlideLoader(this@GigDetailActivity).loadProductPicture(
            product.profileImage,
            iv_user_photo
        )
        tv_product_details_name.text=product.user_name
        tv_product_details_title.text = product.title
        tv_product_details_price.text = product.price
        tv_product_details_description.text = product.description
//        tv_product_details_stock_quantity.text = product.stock_quantity
        phoneNumber=product.description
    }
}