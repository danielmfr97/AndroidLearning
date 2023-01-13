package com.example.cleanarchitecturelearn.framework.presentation.notelist

import android.os.Bundle
import android.view.*
import com.example.cleanarchitecturelearn.framework.presentation.common.BaseNoteFragment
import com.example.cleanarchitecturelearn.R


const val NOTE_LIST_STATE_BUNDLE_KEY = "com.example.cleanarchitecturelearn.framework.presentation.notelist.state"

class NoteListFragment : BaseNoteFragment(R.layout.fragment_note_list)
{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun inject() {
        TODO("prepare dagger")
    }

}










































