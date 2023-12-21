package com.chobo.onrest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommunityAdapter(private val context: Context) : RecyclerView.Adapter<CommunityAdapter.ViewHolder>() {
    var datas = mutableListOf<CommunityData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comunity_list_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: CommunityAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val detail: TextView = itemView.findViewById(R.id.detail)
        private val tag1: TextView = itemView.findViewById(R.id.tag1)
        private val tag2: TextView = itemView.findViewById(R.id.tag2)
        private val commentnum: TextView = itemView.findViewById(R.id.dhatglenum)
        private val heartnum: TextView = itemView.findViewById(R.id.heartnum)
        private val detailButton: Button = itemView.findViewById(R.id.detail_button)


        fun bind(item: CommunityData){
            title.text = item.title
            detail.text = item.detail
            tag1.text = item.tag1
            tag2.text = item.tag2
            commentnum.text = item.comment.toString()
            heartnum.text = item.heart.toString()

            detailButton.setOnClickListener() {
                context.startActivity(Intent(context, PostDetail::class.java))
            }
        }
    }
}