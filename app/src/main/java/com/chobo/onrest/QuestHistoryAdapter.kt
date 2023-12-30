package com.chobo.onrest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView

class QuestHistoryAdapter(private val context: Context) : RecyclerView.Adapter<QuestHistoryAdapter.ViewHolder>() {

    var datas = mutableListOf<QuestHistoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mission_list_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: QuestHistoryAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val data: TextView = itemView.findViewById(R.id.dateTV)
        private val mission: TextView = itemView.findViewById(R.id.missionTV)
        private val checkTV: ToggleButton = itemView.findViewById(R.id.checkTV)

        fun bind(item: QuestHistoryData){
            data.text = item.date
            mission.text = item.mission
            checkTV.isChecked = item.checkTV
        }
    }
}