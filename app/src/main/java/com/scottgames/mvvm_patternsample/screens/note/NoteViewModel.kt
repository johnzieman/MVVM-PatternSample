package com.scottgames.mvvm_patternsample.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.scottgames.mvvm_patternsample.models.AppNote
import com.scottgames.mvvm_patternsample.utils.Constants.REPOSITORY
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    fun delete(appNote: AppNote, onSuccess: () -> Unit) {
        viewModelScope.launch {
            REPOSITORY.delete(appNote) {
                onSuccess()
            }
        }
    }

}