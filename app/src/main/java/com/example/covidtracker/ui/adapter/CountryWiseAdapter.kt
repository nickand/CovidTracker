package com.example.covidtracker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.covidtracker.data.model.CountryStat
import com.example.covidtracker.extensions.filterColombia
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
        filterColombia(list.toMutableList(), this.list, originalList)
        notifyDataSetChanged()
    }

    fun getList(): MutableList<CountryStat> {
        return list
    }

    fun getOriginalList(): MutableList<CountryStat> {
        return originalList
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