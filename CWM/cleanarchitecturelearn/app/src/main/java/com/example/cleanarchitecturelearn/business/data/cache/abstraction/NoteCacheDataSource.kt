package com.example.cleanarchitecturelearn.business.data.cache.abstraction

import androidx.annotation.VisibleForTesting
import com.example.cleanarchitecturelearn.business.domain.model.Note

interface NoteCacheDataSource {

    suspend fun insertNote(note: Note): Long

    suspend fun deleteNote(primaryKey: String): Int

    suspend fun deleteNotes(notes: List<Note>): Int

    suspend fun updateNote(primaryKey: String, newTitle: String, newBody: String): Int

    suspend fun searchNotes(query: String, filterAndOrder: String, page: Int): List<Note>

    suspend fun searchNoteById(primaryKey: String): Note?

    suspend fun getNumNotes(): Int

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun insertNotes(notes: List<Note>): LongArray
}