package com.example.quanlythoikhoabieu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ScheduleListAdapter : ListAdapter<ScheduleItem, ScheduleListAdapter.ScheduleItemViewHolder>(ScheduleItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ScheduleItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ScheduleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subjectNameTextView: TextView = itemView.findViewById(R.id.tv_subject_name)
        private val timeAndDayTextView: TextView = itemView.findViewById(R.id.tv_time_and_day)
        private val roomTextView: TextView = itemView.findViewById(R.id.tv_room)

        fun bind(item: ScheduleItem) {
            subjectNameTextView.text = item.subjectName
            timeAndDayTextView.text = "${item.dayOfWeek} | ${item.startTime} - ${item.endTime}"
            roomTextView.text = "Phòng: ${item.room ?: "Không có"}"
        }
    }

    class ScheduleItemDiffCallback : DiffUtil.ItemCallback<ScheduleItem>() {
        override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
            return oldItem == newItem
        }
    }
}