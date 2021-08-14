package com.example.workersapp.activites.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workersapp.R
import com.example.workersapp.activites.CategoryActivity
import com.example.workersapp.activites.GigActivity
import com.example.workersapp.activites.ProfileActivity
import com.example.workersapp.activites.WoekrGigManeuActivity
import com.example.workersapp.activites.ui.profile.ProfileViewModel
import com.example.workersapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {




    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null


    private val binding get() = _binding!!





    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProfile
        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val profileCard : CardView = root.findViewById<CardView>(R.id.card_my_profile)
        profileCard.setOnClickListener{

            requireActivity().run{
                startActivity(Intent(this, ProfileActivity::class.java))


            }

        }

        val gigCard : CardView = root.findViewById<CardView>(R.id.card_my_gig)
        gigCard.setOnClickListener{

            requireActivity().run{
                startActivity(Intent(this, WoekrGigManeuActivity::class.java))


            }

        }

        return root
    }



    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }





}

