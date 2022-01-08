package com.scottgames.mvvm_patternsample.screens.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scottgames.mvvm_patternsample.MainActivity
import com.scottgames.mvvm_patternsample.R
import com.scottgames.mvvm_patternsample.models.AppNote
import com.scottgames.mvvm_patternsample.utils.FragmentNavigator
import kotlinx.android.synthetic.main.note_item.view.*

class MainAdapter(private val navigator: FragmentNavigator) : RecyclerView.Adapter<MainAdapter.MainHolder>(), View.OnClickListener {
    private var mListNotes = emptyList<AppNote>()



    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.item_note_name
        val textNote: TextView = view.item_note_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        view.setOnClickListener(this)
        return MainHolder(view)
    }

    override fun onClick(v: View?) {

        val note = v?.tag as AppNote
        Log.d("working", "clicked ${note.name}")
        navigator.onOpenMainToNoteFragment(note)
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }

    override fun getItemCount(): Int = mListNotes.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.textNote.text = mListNotes[position].text
        holder.nameNote.text = mListNotes[position].name
        val note = mListNotes[position]
        holder.itemView.tag = note

    }

    fun setList(list: List<AppNote>) {
        mListNotes = list
        notifyDataSetChanged()
    }
}