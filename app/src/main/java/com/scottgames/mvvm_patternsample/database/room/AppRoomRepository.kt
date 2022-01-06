package com.scottgames.mvvm_patternsample.database.room

import androidx.lifecycle.LiveData
import com.scottgames.mvvm_patternsample.database.DataBaseRepository
import com.scottgames.mvvm_patternsample.models.AppNote

class AppRoomRepository(private val dao: AppRoomDao): DataBaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = dao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        dao.insert(note)
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        dao.delete(note)
    }
}