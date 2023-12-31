package com.chobo.onrest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView

class QuestHistoryAdapter(
    private val context: Context,
    private val toggleStateChangeListener: ToggleStateChangeListener
) : RecyclerView.Adapter<QuestHistoryAdapter.ViewHolder>() {
    var datas = mutableListOf<QuestHistoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mission_list_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: QuestHistoryAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
        val currentItem = datas[position]

        if (currentItem.checkTV) {
            holder.checkTV.isClickable = false
            holder.checkTV.isEnabled = false
        } else {
            holder.checkTV.isClickable = true
            holder.checkTV.isEnabled = true
            // 토글 버튼 상태 변경 리스너 설정
            holder.checkTV.setOnCheckedChangeListener { _, isChecked ->
                toggleStateChangeListener.onToggleStateChanged(currentItem.mission, isChecked, position)
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