package com.scottgames.mvvm_patternsample.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.scottgames.mvvm_patternsample.models.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDataBase : RoomDatabase() {
    abstract fun appRoomDao(): AppRoomDao

    companion object {
        @Volatile
        private var dataBase: AppRoomDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDataBase {
            return if (dataBase == null) {
                dataBase = Room.databaseBuilder(
                    context,
                    AppRoomDataBase::class.java,
                    "databese"
                ).build()
                dataBase as AppRoomDataBase
            } else {
                dataBase as AppRoomDataBase
            }
        }
    }
}