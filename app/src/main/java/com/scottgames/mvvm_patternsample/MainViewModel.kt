package com.scottgames.mvvm_patternsample

import android.app.Application
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application, someText: String): AndroidViewModel(application) {
    private var _time = MutableLiveData<String>()
    val time = _time
    init {
        startTimer(someText)
    }

    private fun startTimer(text: String){
        object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                _time.value = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}