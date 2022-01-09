package com.scottgames.mvvm_patternsample.database

import androidx.lifecycle.LiveData
import com.scottgames.mvvm_patternsample.models.AppNote

interface DataBaseRepository {
    val allNotes: LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess: () -> Unit)
    suspend fun delete(note: AppNote, onSuccess: () -> Unit)
    fun connectToFirebase(
        onSuccess: () -> Unit,
        onFailure: (text: String) -> Unit,
        email: String,
        password: String
    ){}
    fun signOut(){}

}