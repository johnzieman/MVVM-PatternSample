package com.scottgames.mvvm_patternsample.screens.start

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.scottgames.mvvm_patternsample.utils.ViewModelFactory
import com.scottgames.mvvm_patternsample.databinding.FragmentStartBinding
import com.scottgames.mvvm_patternsample.utils.FragmentNavigator


class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    private var fragmentNavigator: FragmentNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentNavigator = context as FragmentNavigator
    }

    private val startFragmentViewModel: StartFragmentViewModel by viewModels {
        ViewModelFactory(
            requireActivity().application, ""
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)

        binding.btnRoom.setOnClickListener {
            initDatabase("type_room")
        }

        return binding.root
    }

    private fun initDatabase(type: String){
        startFragmentViewModel.initDatabase(type){
            fragmentNavigator?.onOpenStartToMainFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentNavigator = null
    }
}