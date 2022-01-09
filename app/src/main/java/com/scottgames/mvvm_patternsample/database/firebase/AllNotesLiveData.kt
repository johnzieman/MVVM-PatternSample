package com.scottgames.mvvm_patternsample.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.scottgames.mvvm_patternsample.models.AppNote

class AllNotesLiveData: LiveData<List<AppNote>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val mDataBaseReference = FirebaseDatabase.getInstance().reference.child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNote::class.java)?: AppNote()
            }
        }
        override fun onCancelled(error: DatabaseError) {}
    }

    override fun onActive() {
        super.onActive()
        mDataBaseReference.addValueEventListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        mDataBaseReference.removeEventListener(listener)
    }
}