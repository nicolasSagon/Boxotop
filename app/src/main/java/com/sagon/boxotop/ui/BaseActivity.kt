package com.sagon.boxotop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    inline fun <reified T : ViewModel> getViewModel(activity: AppCompatActivity): T {
        return ViewModelProviders.of(activity)[T::class.java]
    }


}