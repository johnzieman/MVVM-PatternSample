package com.scottgames.mvvm_patternsample.screens.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.scottgames.mvvm_patternsample.databinding.FragmentMainBinding
import com.scottgames.mvvm_patternsample.models.AppNote
import com.scottgames.mvvm_patternsample.utils.FragmentNavigator
import com.scottgames.mvvm_patternsample.utils.ViewModelFactory


class MainFragment : Fragment() {
    private lateinit var mainAdapter: MainAdapter
    private lateinit var mObserever: androidx.lifecycle.Observer<List<AppNote>>
    private var fragmentNavigator: FragmentNavigator? = null
    private val mainFragmentViewModel: MainFragmentViewModel by viewModels {
        ViewModelFactory(
            requireActivity().application,
            ""
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentNavigator = context as FragmentNavigator
    }

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        mainAdapter = MainAdapter()
        binding.recyclerView.adapter = mainAdapter
        mObserever = Observer {
            mainAdapter.setList(it.reversed())
        }

        mainFragmentViewModel.allNotes.observe(viewLifecycleOwner, mObserever)

        binding.btnAddNote.setOnClickListener {
            fragmentNavigator?.onOpenMainToAddNoteFragment()
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentNavigator = null
    }
}