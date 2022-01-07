package com.scottgames.mvvm_patternsample.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.scottgames.mvvm_patternsample.database.DataBaseRepository
import com.scottgames.mvvm_patternsample.database.room.AppRoomDataBase
import com.scottgames.mvvm_patternsample.database.room.AppRoomRepository
import com.scottgames.mvvm_patternsample.utils.Constants.REPOSITORY

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            "type_room" -> {
                val dao = AppRoomDataBase.getInstance(context).appRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}