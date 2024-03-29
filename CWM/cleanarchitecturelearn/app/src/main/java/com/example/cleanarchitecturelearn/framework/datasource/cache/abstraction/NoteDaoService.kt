package com.example.cleanarchitecturelearn.framework.datasource.cache.abstraction

import androidx.annotation.VisibleForTesting
import com.example.cleanarchitecturelearn.business.domain.model.Note
import com.example.cleanarchitecturelearn.framework.datasource.database.NOTE_PAGINATION_PAGE_SIZE

interface NoteDaoService {

    suspend fun insertNote(note: Note): Long

    suspend fun deleteNote(primaryKey: String): Int

    suspend fun deleteNotes(notes: List<Note>): Int

    suspend fun getAllNotes(): List<Note>

    suspend fun updateNote(
        primaryKey: String,
        title: String,
        body: String?,
        timestamp: String?
    ): Int

    suspend fun searchNotesOrderByDateDESC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNotesOrderByDateASC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNotesOrderByTitleDESC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNotesOrderByTitleASC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNoteById(primaryKey: String): Note?

    suspend fun getNumNotes(): Int

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun insertNotes(notes: List<Note>): LongArray

    suspend fun returnOrderedQuery(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Note>
}
