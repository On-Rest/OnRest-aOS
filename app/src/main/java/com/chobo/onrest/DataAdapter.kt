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
        val itemList: List<Mission> = listOf(
            Mission(
                date = 1,
                mission = "안녕0",
                missionDetail = "인사",
                check = false
            ),
            Mission(
                date = 1,
                mission = "안녕2",
                missionDetail = "인사해",
                check = false
            ),
            Mission(
                date = 1,
                mission = "안녕3",
                missionDetail = "인사해2",
                check = false
            )

        )

        val missionDate = view.findViewById<TextView>(R.id.dateTV)
        val missionMission = view.findViewById<TextView>(R.id.missionTV)
        val missionMissionDetail = view.findViewById<TextView>(R.id.missiondetailTV)
        val missionCheck = view.findViewById<TextView>(R.id.checkTV)

        val mission1 = missionList[position]

        missionDate.text = itemList[position].date.toString()
        missionMission.text = itemList[position].mission
        missionMissionDetail.text = itemList[position].missionDetail
        missionCheck.text = itemList[position].check.toString()
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