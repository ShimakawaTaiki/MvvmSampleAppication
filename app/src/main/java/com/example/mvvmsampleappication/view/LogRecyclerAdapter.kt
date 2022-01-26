package com.example.mvvmsampleappication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleappication.R
import com.example.mvvmsampleappication.data.LEVEL
import com.example.mvvmsampleappication.data.StepCountLog
import com.example.mvvmsampleappication.data.WEATHER
import com.example.mvvmsampleappication.databinding.ItemStepLogBinding
import kotlinx.android.synthetic.main.item_step_log.view.*

class LogRecyclerAdapter(private var list: List<StepCountLog>): RecyclerView.Adapter<LogRecyclerAdapter.LogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding: ItemStepLogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_step_log,
            parent,
            false
        )
        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        if (position >= list.size) return
        holder.binding.stepLog = list[position]
    }

    override fun getItemCount(): Int = list.size

    fun setList(newList: List<StepCountLog>) {
        list = newList
        notifyDataSetChanged()
    }

    class LogViewHolder(val binding: ItemStepLogBinding): RecyclerView.ViewHolder(binding.root)
}