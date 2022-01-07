package com.scottgames.mvvm_patternsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.scottgames.mvvm_patternsample.databinding.ActivityMainBinding
import com.scottgames.mvvm_patternsample.utils.FragmentNavigator

class MainActivity : AppCompatActivity(), FragmentNavigator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "Notes"

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
    }

    override fun onOpenStartToMainFragment() {
        navController.navigate(R.id.action_startFragment_to_mainFragment)
    }

    override fun onOpenMainToAddNoteFragment() {
        navController.navigate(R.id.action_mainFragment_to_addNewNotesFragment)
    }

    override fun onOpenAddNotetoMainFragment() {
        navController.navigate(R.id.action_addNewNotesFragment_to_mainFragment)
    }
}