package com.megatrust.Task.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.megatrust.Task.MainActivity
import com.megatrust.Task.R
import com.megatrust.Task.data.api.ApiClient
import com.megatrust.Task.data.repository.MainRepository
import com.megatrust.Task.databinding.FragmentSecondBinding
import com.megatrust.Task.ui.base.MyViewModelFactory
import com.megatrust.Task.ui.main.adapter.JobsAdapter
import com.megatrust.Task.ui.main.viewmodel.JobsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class JobsFragment : Fragment() {

    lateinit var viewModel: JobsViewModel
    private val retrofitService = ApiClient.getInstance()
    private val adapter = JobsAdapter()
    lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(JobsViewModel::class.java)
        val jobList: RecyclerView = binding.recyclerview
        jobList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.JobList.observe( viewLifecycleOwner, Observer {
            adapter.setjobslist(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })
    }

    fun OnClickListen(){
        findNavController().navigate(R.id.action_SecondFragment_to_fragmentDetails)
    }
}