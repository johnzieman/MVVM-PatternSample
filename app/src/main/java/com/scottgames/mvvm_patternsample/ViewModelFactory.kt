package com.scottgames.mvvm_patternsample

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scottgames.mvvm_patternsample.screens.start.StartFragmentViewModel

class ViewModelFactory(val application: Application, val text: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> {
                MainViewModel(application, text) as T
            }
            StartFragmentViewModel::class.java -> {
                StartFragmentViewModel(application) as T
            }
            else -> throw ClassNotFoundException()
        }

    }
}