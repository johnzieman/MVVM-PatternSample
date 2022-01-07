package com.scottgames.mvvm_patternsample.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.scottgames.mvvm_patternsample.utils.Constants.REPOSITORY

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes
}