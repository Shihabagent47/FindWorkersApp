package com.example.workersapp.activites

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.annotation.RequiresApi
import com.example.workersapp.R
import com.example.workersapp.firestore.FirestoreClass
import com.example.workersapp.models.User
import com.example.workersapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : CardActivity(), View.OnClickListener {

lateinit var lit1:String
lateinit var lon1:String

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }

        // Click event assigned to Forgot Password text.
        tv_forgot_password.setOnClickListener(this)

        btn_login.setOnClickListener(this)

        tv_register.setOnClickListener(this)

        val intent = intent
        val extras = intent.extras
        val litetude = extras!!.getString("latitudeText")
        val longetude = extras!!.getString("longitudeText")

        if (litetude != null) {
            lit1=litetude
        }
        if (longetude != null) {
            lon1=longetude
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.tv_forgot_password -> {

                    val intent = Intent(this@LoginActivity, ForgetPassActivity::class.java)
                    startActivity(intent)

                }

                R.id.btn_login -> {

                    logInRegisteredUser()
                }

                R.id.tv_register -> {

                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }


    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                true
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun logInRegisteredUser() {

        if (validateLoginDetails()) {

            showProgressDialog(resources.getString(R.string.please_wait))


            // Get the text from editText and trim the space
            val email = et_email.text.toString().trim { it <= ' ' }
            val password = et_password.text.toString().trim { it <= ' ' }

            // Log-In using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        FirestoreClass().getUserDetails(this@LoginActivity)
                    } else {
                        // Hide the progress dialog
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

    fun userLoggedInSuccess(user: User) {

        // Hide the progress dialog.
        hideProgressDialog()

        if (user.profileCompleted == 0) {
            // If the user profile is incomplete then launch the UserProfileActivity.
            val intent = Intent(this@LoginActivity, UserProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        } else {
            // Redirect the user to Dashboard Screen after log in.

            val intent = Intent(this, DashBordActivity::class.java)
            val extras = Bundle()
            extras.putString("latitudeText", lit1)
            extras.putString("longitudeText", lon1)
            intent.putExtras(extras)
            startActivity(intent)


        }
        finish()
    }
}