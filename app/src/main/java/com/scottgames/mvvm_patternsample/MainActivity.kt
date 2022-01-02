package com.scottgames.mvvm_patternsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private val mainViewModel: MainViewModel by viewModels { ViewModelFactory(application, "Hello") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        mainViewModel.time.observe(this){
            textView.text = it
        }
    }
}