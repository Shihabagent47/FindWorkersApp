package com.example.workersapp.activites.ui.fragments

import BaseFragment
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.workersapp.activites.CategoryActivity
import com.example.workersapp.R
import com.example.workersapp.activites.DashBordActivity
import com.example.workersapp.activites.LoginActivity
import com.example.workersapp.databinding.FragmentProductsBinding
import kotlinx.android.synthetic.main.fragment_products.*


class ProductsFragment : BaseFragment() {
    var lit:String =""
    var lon:String=""

    private lateinit var mRootView: View
    private var _binding: FragmentProductsBinding? = null


    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val activity: DashBordActivity? = activity as DashBordActivity?
        val litatuide: String? = activity?.getMyLitData()
        val longatude: String? = activity?.getMyLonData()

        if (litatuide != null) {
            lit=litatuide
        }
        if (longatude != null) {
            lon=longatude
        }

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mRootView = inflater.inflate(R.layout.fragment_products, container, false)

//
//        val bt=mRootView.findViewById<Button>(R.id.insideBt)
//
//        bt.setOnClickListener {
//
//
//
//        val insideCat=CategoryFragment()
//        val transaction:FragmentTransaction=childFragmentManager.beginTransaction()
//        transaction.replace(R.id.darkLight,insideCat)
//        transaction.commit()
//
//    }

        val painterCard : CardView = mRootView.findViewById<CardView>(R.id.card_view_painter)
        painterCard.setOnClickListener{

            requireActivity().run{
//                startActivity(Intent(this, CategoryActivity::class.java).putExtra("Username","Color Man"))

                val intent = Intent(this, CategoryActivity::class.java)
                val extras = Bundle()
                extras.putString("latitudeText", lit)
                extras.putString("longitudeText", lon)
                extras.putString("Username", "Color Man")
                intent.putExtras(extras)
                startActivity(intent)

            }

        }

        val cleanerCard : CardView = mRootView.findViewById<CardView>(R.id.card_view_cleaner)
        cleanerCard.setOnClickListener{

            requireActivity().run{
//                startActivity(Intent(this, CategoryActivity::class.java).putExtra("Username","Cleaner"))

                val intent = Intent(this, CategoryActivity::class.java)
                val extras = Bundle()
                extras.putString("latitudeText", lit)
                extras.putString("longitudeText", lon)
                extras.putString("Username", "Cleaner")
                intent.putExtras(extras)
                startActivity(intent)
            }

        }

        val masonCard : CardView = mRootView.findViewById<CardView>(R.id.card_view_mason)
        masonCard.setOnClickListener{

            requireActivity().run{
//                startActivity(Intent(this, CategoryActivity::class.java).putExtra("Username","Mason"))

                val intent = Intent(this, CategoryActivity::class.java)
                val extras = Bundle()
                extras.putString("latitudeText", lit)
                extras.putString("longitudeText", lon)
                extras.putString("Username", "Mason")
                intent.putExtras(extras)
                startActivity(intent)

            }

        }

        val elcectricCard : CardView = mRootView.findViewById<CardView>(R.id.card_view_electrican)
        elcectricCard.setOnClickListener{

            requireActivity().run{
//                startActivity(Intent(this, CategoryActivity::class.java).putExtra("Username","Electrician"))

                val intent = Intent(this, CategoryActivity::class.java)
                val extras = Bundle()
                extras.putString("latitudeText", lit)
                extras.putString("longitudeText", lon)
                extras.putString("Username", "Electrician")
                intent.putExtras(extras)
                startActivity(intent)
            }

        }


        val moversCard : CardView = mRootView.findViewById<CardView>(R.id.card_view_mover)
        moversCard.setOnClickListener{

            requireActivity().run{
//                startActivity(Intent(this, CategoryActivity::class.java).putExtra("Username","movers"))
                val intent = Intent(this, CategoryActivity::class.java)
                val extras = Bundle()
                extras.putString("latitudeText", lit)
                extras.putString("longitudeText", lon)
                extras.putString("Username", "movers")
                intent.putExtras(extras)
                startActivity(intent)

            }

        }

        val plumberCard : CardView = mRootView.findViewById<CardView>(R.id.card_view_plumber)
        plumberCard.setOnClickListener{

            requireActivity().run{
//                startActivity(Intent(this, CategoryActivity::class.java).putExtra("Username","plumber"))

                val intent = Intent(this, CategoryActivity::class.java)
                val extras = Bundle()
                extras.putString("latitudeText", lit)
                extras.putString("longitudeText", lon)
                extras.putString("Username", "plumber")
                intent.putExtras(extras)
                startActivity(intent)
            }

        }




        val CraftmanCard : CardView = mRootView.findViewById<CardView>(R.id.card_view_craftman)
        CraftmanCard.setOnClickListener{

            requireActivity().run{
//                startActivity(Intent(this, CategoryActivity::class.java).putExtra("Username","craftman"))

                val intent = Intent(this, CategoryActivity::class.java)
                val extras = Bundle()
                extras.putString("latitudeText", lit)
                extras.putString("longitudeText", lon)
                extras.putString("Username", "craftman")
                intent.putExtras(extras)
                startActivity(intent)
            }

        }

        return mRootView

        // END
    }
}