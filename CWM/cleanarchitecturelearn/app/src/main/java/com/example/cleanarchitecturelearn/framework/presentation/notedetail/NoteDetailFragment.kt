package com.example.cleanarchitecturelearn.framework.presentation.notedetail

import android.os.Bundle
import android.view.View
import com.example.cleanarchitecturelearn.R
import com.example.cleanarchitecturelearn.framework.presentation.common.BaseNoteFragment

const val NOTE_DETAIL_STATE_BUNDLE_KEY = "com.example.cleanarchitecturelearn.framework.presentation.notelist.state"

class NoteDetailFragment : BaseNoteFragment(R.layout.fragment_note_detail) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun inject() {
        TODO("prepare dagger")
    }


}














