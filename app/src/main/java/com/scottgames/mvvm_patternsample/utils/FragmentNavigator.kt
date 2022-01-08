package com.scottgames.mvvm_patternsample.utils

import com.scottgames.mvvm_patternsample.models.AppNote

interface FragmentNavigator {
    fun onOpenStartToMainFragment()
    fun onOpenMainToAddNoteFragment()
    fun onOpenAddNotetoMainFragment()
    fun onOpenMainToNoteFragment(appNote: AppNote)
    fun onOpenNoteToMainFragment()
}