package com.chobo.onrest

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MainListAdapter(private val context: Context, val missionList: ArrayList<Mission>) : BaseAdapter() {
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.mission_list_view, null)

        val missionDate = view.findViewById<TextView>(R.id.dateTV)
        val missionMission = view.findViewById<TextView>(R.id.missionTV)
        val missionMissionDetail = view.findViewById<TextView>(R.id.missiondetailTV)
        val missionCheck = view.findViewById<TextView>(R.id.checkTV)

        val mission1 = missionList[position]
        missionDate.text = Mission.date
        missionMission.text = Mission.age
        missionMissionDetail.text = Mission.missionDetail
        missionCheck.text = Mission.check

        return view
    }

    override fun getItem(position: Int): Any {
        return missionList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return missionList.size
    }
}
