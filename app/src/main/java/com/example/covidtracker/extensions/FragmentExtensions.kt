package com.example.covidtracker.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.covidtracker.R
import com.example.covidtracker.ui.detail.DetailFragment

fun Fragment.selectTab(destId: Int, args: Bundle? = null) = findNavController()
    .navigate(destId, args, navOptions {
        popUpTo(findNavController().graph.startDestination) { inclusive = true }
    })

/**
 * Adds a [Fragment] in the backstack in the [FragmentManager]
 *
 * @param fragment the fragment to be added.
 */
fun addFragment(
    supportFragmentManager: FragmentManager, @IdRes idResContainer: Int,
    fragment: Fragment
) {
    val transaction = supportFragmentManager.beginTransaction()
    if (fragment is DetailFragment) {
        transaction.add(idResContainer, fragment)
        transaction.commit()
    }
}

/**
 * Replace a [Fragment] in the backstack in the [FragmentManager]
 *
 * @param fragment the fragment to be added.
 */
fun replaceFragment(
    supportFragmentManager: FragmentManager, @IdRes idResContainer: Int,
    fragment: Fragment
) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(idResContainer, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}