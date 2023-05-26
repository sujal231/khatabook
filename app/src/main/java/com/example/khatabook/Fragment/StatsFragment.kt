package com.example.khatabook.Fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.khatabook.DbHelper
import com.example.khatabook.databinding.FragmentStatsBinding
import com.example.khatabook.listModal
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class StatsFragment : Fragment() {

    lateinit var dbHelper: DbHelper
    lateinit var binding: FragmentStatsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatsBinding.inflate(layoutInflater)

        dbHelper = DbHelper(context)

        var list = dbHelper.get()



        total(list)
//        Chart()


        return binding.root
    }

    fun total(list: ArrayList<listModal>) {

        var totalin = 0
        var totalex = 0
        for (trans in list) {


            if (trans.isExpense == 0) {
                totalin += trans.amount
            } else {
                totalex += trans.amount
            }
        }

        binding.txtin.text = totalin.toString()
        binding.txtex.text = totalex.toString()
        binding.txttotel.text = (totalin - totalex).toString()

        Chart(totalin, totalex)
    }

    fun Chart(totalin: Int, totalex: Int) {
        val pieEntries = ArrayList<PieEntry>()
        val label = "type"


        //initializing data
        val typeAmountMap: MutableMap<String, Int> = HashMap()

        typeAmountMap["Income"] = totalex
        typeAmountMap["Expense"] = totalin


        //initializing colors for the entries
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#304567"))
        colors.add(Color.parseColor("#309967"))

        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, label)
        //setting text size of the value
        pieDataSet.valueTextSize = 12f
        //providing color list for coloring different entries
        pieDataSet.colors = colors
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true)
        binding.piechart.setData(pieData)
        binding.piechart.invalidate()
    }

}