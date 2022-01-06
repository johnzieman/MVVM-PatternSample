package com.scottgames.mvvm_patternsample.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.scottgames.mvvm_patternsample.database.DataBaseRepository
import com.scottgames.mvvm_patternsample.database.room.AppRoomDataBase
import com.scottgames.mvvm_patternsample.database.room.AppRoomRepository

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    private var repository: DataBaseRepository? = null
    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            "type_room" -> {
                val dao = AppRoomDataBase.getInstance(context).appRoomDao()
                repository = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}