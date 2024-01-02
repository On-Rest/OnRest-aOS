package com.chobo.onrest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chobo.onrest.dto.GetPostResponse

class CommunityAdapter(private val context: Context) : RecyclerView.Adapter<CommunityAdapter.ViewHolder>() {
    var datas = mutableListOf<GetPostResponse>()
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
        private val heartnum: TextView = itemView.findViewById(R.id.heartnum)
        private val detailButton: Button = itemView.findViewById(R.id.detail_button)


        fun bind(item: GetPostResponse){
            title.text = item.subject
            detail.text = item.detail
            tag1.text = item.emotion.toString()
            tag2.text = item.emotion.toString()
            heartnum.text = item.like_count.toString()

            detailButton.setOnClickListener() {
                context.startActivity(Intent(context    , PostDetail::class.java))
            }
        }
    }

}