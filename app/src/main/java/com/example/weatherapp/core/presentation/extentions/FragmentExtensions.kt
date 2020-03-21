package com.example.weatherapp.core.presentation.extentions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int, addToBackStack: Boolean = false, tag: String = ""
) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}


fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int, addToBackStack: Boolean = false, tag: String = ""
) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}

fun FragmentActivity.addFragment(
    fragment: Fragment,
    frameId: Int, addToBackStack: Boolean = false, tag: String = ""
) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}


fun Fragment.replaceFragment(
    fragment: Fragment,
    frameId: Int, addToBackStack: Boolean = false, name: String = ""
) {
    activity?.replaceFragment(fragment, frameId, addToBackStack, name)
}


fun Fragment.addFragment(
    fragment: Fragment,
    frameId: Int, addToBackStack: Boolean = false, name: String = ""
) {
    activity?.addFragment(fragment, frameId, addToBackStack, name)
}

fun Fragment.remove(fragment: Fragment) {
    activity?.supportFragmentManager?.inTransaction {
        remove(fragment)
    }
}


fun Fragment.showToast(msg: String, duration: Int = 1) {
    activity?.showToast(msg, duration)
}