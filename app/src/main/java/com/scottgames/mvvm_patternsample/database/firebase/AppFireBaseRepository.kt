package com.scottgames.mvvm_patternsample.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.scottgames.mvvm_patternsample.database.DataBaseRepository
import com.scottgames.mvvm_patternsample.models.AppNote

class AppFireBaseRepository: DataBaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = AllNotesLiveData()

    private val mAuth = FirebaseAuth.getInstance()
    private val mDataBaseReference = FirebaseDatabase.getInstance().reference.child(mAuth.currentUser?.uid.toString())

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = mDataBaseReference.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote["id_firebase"] = idNote
        mapNote["name"] = note.name
        mapNote["text"] = note.text

        mDataBaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                Log.d("mistake", it.message.toString())
            }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {

    }

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