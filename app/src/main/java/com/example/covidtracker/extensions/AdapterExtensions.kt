package com.example.covidtracker.extensions

import com.example.covidtracker.data.model.CountryStat
import com.example.covidtracker.ui.adapter.CountryWiseAdapter

fun CountryWiseAdapter.search(list: MutableList<CountryStat>, originalList: MutableList<CountryStat>, query: String) {
    list.clear()
    list.addAll(originalList)

    if (query.isNotEmpty()) {
        var index = 0

        while (index < list.size) {
            if (list[index].countryName.toLowerCase().contains(query.toLowerCase()).not()) {
                list.removeAt(index)
                index--
            }
            index++
        }
    }

    notifyDataSetChanged()
}

fun filterColombia(list: List<CountryStat>, newList: MutableList<CountryStat>,
                   originalList: MutableList<CountryStat>) {
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
        newList.add(0, colItem)
        newList.removeAt(colIndex + 1)

        originalList.add(0, colItem)
        originalList.removeAt(colIndex + 1)
    }
}