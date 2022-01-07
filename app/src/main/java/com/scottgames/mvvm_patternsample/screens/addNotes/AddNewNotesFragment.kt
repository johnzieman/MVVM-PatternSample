package com.scottgames.mvvm_patternsample.screens.addNotes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.scottgames.mvvm_patternsample.R
import com.scottgames.mvvm_patternsample.databinding.FragmentAddNewNotesBinding
import com.scottgames.mvvm_patternsample.models.AppNote
import com.scottgames.mvvm_patternsample.utils.FragmentNavigator


class AddNewNotesFragment : Fragment() {
    private val addNewNotesViewModel: AddNewNotesViewModel by viewModels()
    private lateinit var binding: FragmentAddNewNotesBinding
    private var fragmentNavigator: FragmentNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentNavigator = context as FragmentNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewNotesBinding.inflate(layoutInflater, container, false)

        binding.btnAddNote.setOnClickListener {
            val name = binding.inputNameNote.text.toString()
            val text = binding.inputTextNote.text.toString()

            if (name.isEmpty()) {
                addNewNotesViewModel.showToast("Name fiels is empty")
            } else {
                val appNote = AppNote(name = name, text = text)
                addNewNotesViewModel.insert(appNote) {
                    fragmentNavigator?.onOpenAddNotetoMainFragment()
                }
            }
        }

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        fragmentNavigator = null
    }
}