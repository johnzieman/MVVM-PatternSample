package com.scottgames.mvvm_patternsample.database

interface FireBaseRepository {
    fun connectToFirebase(
        onSuccess: () -> Unit,
        onFailure: (text: String) -> Unit,
        email: String,
        password: String
    )

    fun signOut()
}