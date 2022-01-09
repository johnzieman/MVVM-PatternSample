package com.scottgames.mvvm_patternsample.screens.start

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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


        binding.btnFirebase.setOnClickListener{
            binding.btnLogin.visibility = View.VISIBLE
            binding.inputEmail.visibility = View.VISIBLE
            binding.inputPassword.visibility = View.VISIBLE
            binding.btnLogin.setOnClickListener {
                if (binding.inputEmail.text.isEmpty() && binding.inputPassword.text.isEmpty()){
                    Toast.makeText(requireContext(), "Empty fields", Toast.LENGTH_SHORT).show()
                } else {
                    val email = binding.inputEmail.text.toString()
                    val password = binding.inputPassword.text.toString()
                    initFirebase("type_firebase", email, password)
                }
            }
        }

        return binding.root
    }

    private fun initDatabase(type: String){
        startFragmentViewModel.inits(type){
            fragmentNavigator?.onOpenStartToMainFragment()
        }
    }

    private fun initFirebase(type: String, email: String, password: String){
        startFragmentViewModel.inits(type, email = email, password = password){
            fragmentNavigator?.onOpenStartToMainFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentNavigator = null
    }
}