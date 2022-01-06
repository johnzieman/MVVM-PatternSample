package com.scottgames.mvvm_patternsample.screens.start

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.scottgames.mvvm_patternsample.R
import com.scottgames.mvvm_patternsample.ViewModelFactory
import com.scottgames.mvvm_patternsample.databinding.FragmentStartBinding
import com.scottgames.mvvm_patternsample.databinding.NoteItemBinding


class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    private val startFragmentViewModel: StartFragmentViewModel by viewModels {
        ViewModelFactory(
            requireContext() as Application, ""
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}