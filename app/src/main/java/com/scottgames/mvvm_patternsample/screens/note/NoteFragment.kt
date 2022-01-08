package com.scottgames.mvvm_patternsample.screens.note

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.scottgames.mvvm_patternsample.R
import com.scottgames.mvvm_patternsample.databinding.FragmentNoteBinding
import com.scottgames.mvvm_patternsample.models.AppNote
import com.scottgames.mvvm_patternsample.utils.FragmentNavigator
import com.scottgames.mvvm_patternsample.utils.ViewModelFactory

class NoteFragment : Fragment() {
    private val args: NoteFragmentArgs by navArgs()
    private lateinit var appNote: AppNote
    private val noteViewModel: NoteViewModel by viewModels {
        ViewModelFactory(
            requireActivity().application,
            ""
        )
    }
    private lateinit var binding: FragmentNoteBinding
    private var navigator: FragmentNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as FragmentNavigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appNote = args.note
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
          R.id.btn_delete -> {
              noteViewModel.delete(appNote){
                  Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show()
                  navigator?.onOpenNoteToMainFragment()
              }
              true
          }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)

        binding.noteName.text = appNote.name
        binding.noteText.text = appNote.text

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }
}