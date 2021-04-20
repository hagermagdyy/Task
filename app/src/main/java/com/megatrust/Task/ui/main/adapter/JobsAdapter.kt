package com.megatrust.Task.ui.main.adapter

import android.R.attr.data
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.megatrust.Task.R
import com.megatrust.Task.data.model.model
import com.megatrust.Task.data.room.JobEntity


class JobsAdapter: RecyclerView.Adapter<MainViewHolder>(){

    var navController: NavController? = null

    var jobs = mutableListOf<JobEntity>()


    fun setjobslist(movies: List<JobEntity>) {
        this.jobs = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_jobs, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val job = jobs[position]
        holder.companyTextView.text = job.company
        holder.createdat.text = job.created_at
        holder.location.text = job.location
        Glide.with(holder.itemView.context).load(job.company_logo).into(holder.JobImageView)

        holder.parent.setOnClickListener { it ->

            // Can't get Navigation
            navController = Navigation.findNavController(it)
            val context:Context = holder.JobImageView.context

            val pref: SharedPreferences = context.getSharedPreferences("MyPref", 0)
            val editor = pref.edit()

            editor.putInt("ID", job.JobID)
            editor.putString("Image", job.company_logo)
            editor.putString("Description", job.description)
            editor.putString("how_to_apply", job.how_to_apply)
            editor.putString("Created_at", job.created_at)
            editor.putString("Company", job.company)
            editor.putString("Company_url", job.company_url)

            editor.apply()

            navController!!.navigate(R.id.action_SecondFragment_to_fragmentDetails)
        }
    }

    override fun getItemCount(): Int {
        return jobs.size
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val companyTextView: TextView = itemView.findViewById(R.id.textView)
    val JobImageView: ImageView = itemView.findViewById(R.id.imageView)
    val createdat: TextView = itemView.findViewById((R.id.textView2))
    val location: TextView = itemView.findViewById(R.id.textView3)
    val parent:RelativeLayout = itemView.findViewById((R.id.parent_card))
}