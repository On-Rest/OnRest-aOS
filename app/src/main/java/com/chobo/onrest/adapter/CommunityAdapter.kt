package com.chobo.onrest.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chobo.onrest.activity.PostDetail
import com.chobo.onrest.R
import com.chobo.onrest.dataclass.CommunityData

class CommunityAdapter(private val context: Context) : RecyclerView.Adapter<CommunityAdapter.ViewHolder>() {
    var dataList = mutableListOf<CommunityData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comunity_list_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val detailTextView: TextView = itemView.findViewById(R.id.detail)
        private val tag1TextView: TextView = itemView.findViewById(R.id.tag1)
        private val tag2TextView: TextView = itemView.findViewById(R.id.tag2)
        private val commentNumTextView: TextView = itemView.findViewById(R.id.dhatglenum)
        private val heartNumTextView: TextView = itemView.findViewById(R.id.heartnum)
        private val detailButton: Button = itemView.findViewById(R.id.detail_button)

        fun bind(item: CommunityData) {
            titleTextView.text = item.title
            detailTextView.text = item.detail
            tag1TextView.text = item.tag1
            tag2TextView.text = item.tag2
            commentNumTextView.text = item.comment.toString()
            heartNumTextView.text = item.heart.toString()

            detailButton.setOnClickListener() {
                val intent = Intent(context, PostDetail::class.java)
                intent.putExtra("title", titleTextView.text) // 데이터 전달
                intent.putExtra("detail", detailTextView.text) // 데이터 전달
                intent.putExtra("heartnum", heartNumTextView.text) // 데이터 전달
                context.startActivity(intent)
            }
        }
    }
}
