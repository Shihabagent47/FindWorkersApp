package com.example.workersapp.activites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.workersapp.R
import com.example.workersapp.activites.ui.fragments.*


class CategoryActivity : AppCompatActivity() {

    private var mylong = ""
    private var mylit = ""

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val profileName=intent.getStringExtra("Username")
        val lit=intent.getStringExtra("latitudeText")
        val lon=intent.getStringExtra("longitudeText")

        if (lon != null) {
            mylong=lon
        }
        if (lit != null) {
            mylit=lit
        }






 when(profileName)
 {
     "Color Man" -> { val newFragment: Fragment = CategoryFragment()
         val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.catId, newFragment)
         transaction.addToBackStack(null)

         transaction.commit()


     }


     "Cleaner" ->  {  val newFragment: Fragment = CleanerFragment()
         val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.catId, newFragment)
         transaction.addToBackStack(null)

         transaction.commit()}


     "Mason"->{  val newFragment: Fragment = MasonFragment()
         val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.catId, newFragment)
         transaction.addToBackStack(null)

         transaction.commit()}


     "Electrician"->{  val newFragment: Fragment = ElectricanFragment()
         val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.catId, newFragment)
         transaction.addToBackStack(null)

         transaction.commit()}


     "movers"-> {  val newFragment: Fragment = MoversFragment()
         val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.catId, newFragment)
         transaction.addToBackStack(null)

         transaction.commit()}

     "plumber"-> {  val newFragment: Fragment = PlumberFragment()
         val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.catId, newFragment)
         transaction.addToBackStack(null)

         transaction.commit()}


     "craftman"-> {  val newFragment: Fragment = CraftManFragment()
         val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.catId, newFragment)
         transaction.addToBackStack(null)

         transaction.commit()}



 }




    }

    fun getMyLonData(): String? {
        return mylong
    }
    fun getMyLitData(): String? {
        return mylit
    }


    }
