package com.scottgames.mvvm_patternsample.database.firebase

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.scottgames.mvvm_patternsample.database.FireBaseRepository

class AppFireBaseRepository: FireBaseRepository {
    private val auth = FirebaseAuth.getInstance()
    override fun connectToFirebase(onSuccess: () -> Unit, onFailure: (text: String) -> Unit, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener{
                        onFailure(it.message.toString())
                    }
            }
    }

    override fun signOut() {
        auth.signOut()
    }
}