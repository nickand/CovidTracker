package com.example.covidtracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.covidtracker.data.model.CountryStat
import kotlinx.android.synthetic.main.item_list.view.*


class CountryWiseAdapter(
    private val list: MutableList<CountryStat>,
    private val listener: OnEvent) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var originalList = mutableListOf<CountryStat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CountryWiseViewHolder(view)
    }

    fun addData(list: List<CountryStat>) {
        this.list.addAll(list)
        originalList.addAll(list)
        filterColombia(list)
        notifyDataSetChanged()
    }

    private fun filterColombia(list: List<CountryStat>) {
        var colIndex = -1
        var colItem: CountryStat? = null

        for (index in list.indices) {
            if (list[index].countryName == "Colombia") {
                colIndex = index
                colItem = list[index]
                break
            }
        }

        if (colIndex != -1 && colItem != null) {
            this.list.add(0, colItem)
            this.list.removeAt(colIndex + 1)

            originalList.add(0, colItem)
            originalList.removeAt(colIndex + 1)
        }
    }

    fun clear() {
        list.clear()
        originalList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CountryWiseViewHolder)
            holder.bind(list[position])
    }

    fun search(query: String) {
        list.clear()
        this.list.addAll(originalList)

        if (query.isNotEmpty()) {
            var index = 0

            while (index < list.size) {
                if (list[index].countryName.toLowerCase().contains(query.toLowerCase()).not()) {
                    list.removeAt(index)
                    index--
                }
                index++
            }

            listener.logEvent(query)
        }

        notifyDataSetChanged()
    }

    inner class CountryWiseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(countryStat: CountryStat) {
            itemView.apply {
                countryName.text = countryStat.countryName
                newCasesCount.text = countryStat.newCases
                confirmedCount.text = countryStat.totalCases
                recoveredCount.text = countryStat.totalRecovered
                deathCount.text = countryStat.totalDeaths
            }
        }
    }

    interface OnEvent {
        fun logEvent(query: String)
    }
}