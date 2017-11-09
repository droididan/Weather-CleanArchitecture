package com.smilebackapp.weather.core

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.ImageView

/**
 * Created by idan on 08/11/2017.
 * Extentions
 */
fun ImageView.loadImage(iconName:String) {
TODO("Implement")

}

inline fun FragmentManager.performTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}