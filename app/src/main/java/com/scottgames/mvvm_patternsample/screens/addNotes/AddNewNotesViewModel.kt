package com.scottgames.mvvm_patternsample.screens.addNotes

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.scottgames.mvvm_patternsample.database.DataBaseRepository
import com.scottgames.mvvm_patternsample.database.room.AppRoomDataBase
import com.scottgames.mvvm_patternsample.database.room.AppRoomRepository
import com.scottgames.mvvm_patternsample.models.AppNote
import com.scottgames.mvvm_patternsample.utils.Constants.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNewNotesViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun insert(appNote: AppNote, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(appNote){
                viewModelScope.launch {
                    withContext(Dispatchers.Main){
                        onSuccess()
                    }
                }
            }
        }
}