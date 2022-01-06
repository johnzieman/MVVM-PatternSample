package com.scottgames.mvvm_patternsample.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.scottgames.mvvm_patternsample.models.AppNote

@Dao
interface AppRoomDao {

    @Query("SELECT * FROM note_database")
    fun getAllNotes(): LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: AppNote)

    @Delete
    suspend fun delete(appNote: AppNote)
}