package com.megatrust.Task.ui.main.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.megatrust.Task.databinding.FragmentDetailsBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class FragmentDetails : Fragment() {


    private val args: FragmentDetailsArgs by navArgs()
    var Id: String = ""
    var Logo: String = ""
    var Url: String = ""
    var Date: String = ""
    var How_to_Apply: String = ""
    var name:String = ""

    lateinit var binding: FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

        val pref: SharedPreferences =
            requireActivity().getSharedPreferences("MyPref", MODE_PRIVATE)
        Id = pref.getString("ID", "").toString()
        Logo = pref.getString("Image", "").toString()
        How_to_Apply = pref.getString("how_to_apply", "").toString()
        Url = pref.getString("Company_url", "").toString()
        Date = pref.getString("Created_at", "").toString()
        name = pref.getString("Company", "").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        Glide.with(requireActivity()).load(Logo).into(binding.companyLogo)
        binding.comapnyName.text = name
        binding.companyUrl.text = Url
        binding.createdAt.text = Date
        binding.howToApply.text = How_to_Apply
        return binding.root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDetails().apply {
                arguments = Bundle().apply {

                }
            }
    }
}