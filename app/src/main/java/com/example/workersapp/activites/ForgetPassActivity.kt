package com.example.workersapp.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.workersapp.R
import com.google.firebase.auth.FirebaseAuth

class ForgetPassActivity : CardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)

        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        var buttonForgot:Button=findViewById(R.id.btn_submit)

        buttonForgot.setOnClickListener {

            forgetPass()
        }
    }

    private fun forgetPass(){
        var edittext:EditText=findViewById(R.id.et_email)
        var email=edittext.text.toString().trim { it <=' '}

        if (email.isEmpty()){


            showErrorSnackBar(resources.getString(R.string.err_msg_enter_email),true)
        }
        else{

    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
        .addOnCompleteListener{
                task ->
            if(task.isSuccessful){

                Toast.makeText(
                    this ,
                    resources.getString(R.string.email_sent_success),
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
            else{

                showErrorSnackBar(task.exception!!.message.toString(), true)
            }
        }
        }

    }
}