package com.example.mvvmsampleappication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleappication.R
import kotlinx.android.synthetic.main.item_step_log.view.*

class LogRecyclerAdapter(private var list: List<Int>): RecyclerView.Adapter<LogRecyclerAdapter.LogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.item_step_log, parent, false)
        return LogViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.textCount.text = if (position < list.size) list[position].toString() else ""
    }

    override fun getItemCount(): Int = list.size

    fun setList(newList: List<Int>) {
        list = newList
        notifyDataSetChanged()
    }

    class LogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textCount = itemView.step_text_view!!
    }
}