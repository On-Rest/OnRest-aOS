package com.chobo.onrest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chobo.onrest.R
import com.chobo.onrest.dataclass.PostDetailData

class PostDetailAdapter(private val context: Context) : RecyclerView.Adapter<PostDetailAdapter.ViewHolder>()     {
    var DataList = mutableListOf<PostDetailData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post_detail_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = DataList.size
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(DataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = itemView.findViewById(R.id.name)
        private val text: TextView = itemView.findViewById(R.id.detail_text)

        fun bind(item: PostDetailData) {
            name.text = item.name
            text.text = item.text

        }
    }
}