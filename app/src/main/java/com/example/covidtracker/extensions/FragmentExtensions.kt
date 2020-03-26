package com.example.covidtracker.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions

fun Fragment.selectTab(destId: Int, args: Bundle? = null) = findNavController()
    .navigate(destId, args, navOptions {
        popUpTo(findNavController().graph.startDestination) { inclusive = true }
    })