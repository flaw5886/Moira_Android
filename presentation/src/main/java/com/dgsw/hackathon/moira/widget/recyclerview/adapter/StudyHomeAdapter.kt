package com.dgsw.hackathon.moira.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dgsw.hackathon.moira.R
import com.dgsw.hackathon.moira.databinding.ItemStudyHomeBinding
import com.dgsw.hackathon.moira.model.StudyData
import com.dgsw.hackathon.moira.widget.recyclerview.viewmodel.StudyHomeItemViewModel

class StudyHomeAdapter : RecyclerView.Adapter<StudyHomeAdapter.ViewHolder>(){

    private lateinit var studyList : ArrayList<StudyData>

    fun setList(list : ArrayList<StudyData>) {
        if(::studyList.isInitialized) return
        studyList = list
    }

    class ViewHolder(private val binding : ItemStudyHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = StudyHomeItemViewModel()

        fun bind(study: StudyData) {
            viewModel.bind(study)
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_study_home,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if(::studyList.isInitialized) studyList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder) {
            bind(studyList[position])
        }
    }
}