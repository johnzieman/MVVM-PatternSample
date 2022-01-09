package com.scottgames.mvvm_patternsample.screens.start

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.scottgames.mvvm_patternsample.database.DataBaseRepository
import com.scottgames.mvvm_patternsample.database.firebase.AppFireBaseRepository
import com.scottgames.mvvm_patternsample.database.room.AppRoomDataBase
import com.scottgames.mvvm_patternsample.database.room.AppRoomRepository
import com.scottgames.mvvm_patternsample.utils.Constants.REPOSITORY

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    fun inits(type: String, email: String = "", password: String = "", onSuccess: () -> Unit) {
        when (type) {
            "type_room" -> {
                val dao = AppRoomDataBase.getInstance(context).appRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            "type_firebase" -> {
                REPOSITORY = AppFireBaseRepository()
                REPOSITORY.connectToFirebase(
                    { onSuccess() },
                    { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
                    email,
                    password
                )
            }
        }
    }
}