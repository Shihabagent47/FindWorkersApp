package com.example.workersapp.activites

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager

import android.location.Location
import android.net.Uri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.workersapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_woekr_gig_maneu.*
import android.provider.Settings
import com.example.workersapp.BuildConfig
import com.example.workersapp.databinding.ActivityWoekrGigManeuBinding
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*

class WoekrGigManeuActivity : CardActivity() {




    var latitudeText: String=""
    var longitudeText: String=""

    private lateinit var binding: ActivityWoekrGigManeuBinding

    // The Fused Location Provider provides access to location APIs.
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    private var cancellationTokenSource = CancellationTokenSource()


    private val fineLocationRationalSnackbar by lazy {
        Snackbar.make(
            binding.workerGig,
            R.string.fine_location_permission_rationale,
            Snackbar.LENGTH_LONG
        ).setAction(R.string.ok) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        binding = ActivityWoekrGigManeuBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)



        fltActBut.setOnClickListener {
            val intent = Intent(this, GigActivity::class.java)
            val extras = Bundle()
            extras.putString("latitudeText", latitudeText)
            extras.putString("longitudeText", longitudeText)
            intent.putExtras(extras)
            startActivity(intent)


        }


    }





    override fun onStart() {
        super.onStart()
        locationRequestOnClick()

    }





    override fun onStop() {
        super.onStop()
        // Cancels location request (if in flight).
        cancellationTokenSource.cancel()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionResult()")

        if (requestCode == REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() ->
                    // If user interaction was interrupted, the permission request
                    // is cancelled and you receive an empty array.
                    Log.d(TAG, "User interaction was cancelled.")

                grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                    Snackbar.make(
                        binding.workerGig,
                        R.string.permission_approved_explanation,
                        Snackbar.LENGTH_LONG
                    )
                        .show()

                else -> {
                    Snackbar.make(
                        binding.workerGig,
                        R.string.fine_permission_denied_explanation,
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(R.string.settings) {
                            // Build intent that displays the App settings screen.
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                BuildConfig.APPLICATION_ID,
                                null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        .show()
                }
            }
        }
    }

    fun locationRequestOnClick() {
        Log.d(TAG, "locationRequestOnClick()")

        val permissionApproved =
            applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionApproved) {
            requestCurrentLocation()
        } else {
            requestPermissionWithRationale(
                Manifest.permission.ACCESS_FINE_LOCATION,
                REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE,
                fineLocationRationalSnackbar
            )
        }
    }


    @SuppressLint("MissingPermission")
    private fun requestCurrentLocation() {
        Log.d(TAG, "requestCurrentLocation()")
        if (applicationContext.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            showProgressDialog(resources.getString(R.string.tracking_location))

            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                var  lati=""
                var  long =""
                val result = if (task.isSuccessful && task.result != null) {
                    val result: Location = task.result

                    lati=result.latitude.toString()
                    long=result.longitude.toString()


                } else {
                    val exception = task.exception
                    "Location (failure): $exception"
                }

                setLocation(long,lati)
                hideProgressDialog()
            }
        }
    }

    fun setLocation(lon:String,lit:String){
        latitudeText= lit
        longitudeText=lon

    }

    companion object {
        private const val TAG = "MainActivity"
        private const val REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE = 34
    }

}