package com.example.workersapp.activites.ui.fragments

import BaseFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.workersapp.R
import com.example.workersapp.activites.CategoryActivity
import com.example.workersapp.activites.GigDetailActivity
import com.example.workersapp.activites.ui.adapters.DashboardItemsListAdapter
import com.example.workersapp.firestore.FirestoreClass
import com.example.workersapp.models.Workers
import com.example.workersapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_dashboard.*


class ElectricanFragment : BaseFragment() {
    var lit:Double =0.0
    var lon:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // If we want to use the option menu in fragment we need to add it.
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity: CategoryActivity? = activity as CategoryActivity?
        val litatuide: String? = activity?.getMyLitData()
        val longatude: String? = activity?.getMyLonData()

        if (litatuide != null) {
            lit=litatuide.toDouble()
        }
        if (longatude != null) {
            lon=longatude.toDouble()
        }
        val root = inflater.inflate(R.layout.fragment_cleaner, container, false)

        return root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        getDashboardItemsList()
    }

    /**
     * A function to get the dashboard items list from cloud firestore.
     */
    private fun getDashboardItemsList() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getElectricianItemsList(this@ElectricanFragment,"Electrician",lit,lon)
    }

    /**
     * A function to get the success result of the dashboard items from cloud firestore.
     *
     * @param dashboardItemsList
     */
    fun successDashboardItemsList(dashboardItemsList: ArrayList<Workers>) {

        // Hide the progress dialog.
        hideProgressDialog()

        if (dashboardItemsList.size > 0) {

            rv_dashboard_items.visibility = View.VISIBLE
            tv_no_dashboard_items_found.visibility = View.GONE

            rv_dashboard_items.layoutManager = GridLayoutManager(activity, 2)
            rv_dashboard_items.setHasFixedSize(true)

            val adapter = DashboardItemsListAdapter(requireActivity(), dashboardItemsList)
            rv_dashboard_items.adapter = adapter


            adapter.setOnClickListener(object :
                DashboardItemsListAdapter.OnClickListener {
                override fun onClick(position: Int, product: Workers) {


                    val intent = Intent(context, GigDetailActivity::class.java)
                    intent.putExtra(Constants.EXTRA_PRODUCT_ID, product.product_id)
                    startActivity(intent)
                    // END
                }
            })
            // END
        } else {
            rv_dashboard_items.visibility = View.GONE
            tv_no_dashboard_items_found.visibility = View.VISIBLE
        }
    }
}