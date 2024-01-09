package com.chobo.onrest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.chobo.onrest.interFace.ToggleStateChangeListener

class QuestHistoryAdapter(
    private val context: Context,
    private val toggleStateChangeListener: ToggleStateChangeListener
) : RecyclerView.Adapter<QuestHistoryAdapter.ViewHolder>() {
    var dataList = mutableListOf<QuestHistoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mission_list_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size
    override fun onBindViewHolder(holder: QuestHistoryAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
        val currentItem = dataList[position]

        with(holder.checkTV) {
            isClickable = !currentItem.checkTV
            isEnabled = !currentItem.checkTV
            setOnCheckedChangeListener(null)
            isChecked = currentItem.checkTV
            setOnClickListener {
                toggleStateChangeListener.onToggleStateChanged(currentItem.mission, !isChecked, position)
            }
        }
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val data: TextView = itemView.findViewById(R.id.dateTV)
        private val mission: TextView = itemView.findViewById(R.id.missionTV)
        val checkTV: ToggleButton = itemView.findViewById(R.id.checkTV)

        fun bind(item: QuestHistoryData){
            data.text = item.date
            mission.text = item.mission
            checkTV.isChecked = item.checkTV
        }
    }
}