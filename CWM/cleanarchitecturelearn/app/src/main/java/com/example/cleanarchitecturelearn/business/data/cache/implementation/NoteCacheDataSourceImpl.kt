package com.example.cleanarchitecturelearn.business.data.cache.implementation

import androidx.annotation.VisibleForTesting
import com.example.cleanarchitecturelearn.business.data.cache.abstraction.NoteCacheDataSource
import com.example.cleanarchitecturelearn.business.domain.model.Note
import com.example.cleanarchitecturelearn.framework.datasource.cache.abstraction.NoteDaoService
import com.example.cleanarchitecturelearn.framework.datasource.database.NOTE_PAGINATION_PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteCacheDataSourceImpl
@Inject
constructor(private val noteDaoService: NoteDaoService) : NoteCacheDataSource {
    override suspend fun insertNote(note: Note): Long = noteDaoService.insertNote(note)

    override suspend fun deleteNote(primaryKey: String): Int =
        noteDaoService.deleteNote(primaryKey)

    override suspend fun deleteNotes(notes: List<Note>): Int =
        noteDaoService.deleteNotes(notes)

    override suspend fun updateNote(
        primaryKey: String,
        newTitle: String,
        newBody: String?,
        timestamp: String?
    ): Int {
        return noteDaoService.updateNote(
            primaryKey,
            newTitle,
            newBody,
            timestamp
        )
    }
    override suspend fun searchNotes(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Note> {
        return noteDaoService.returnOrderedQuery(
            query, filterAndOrder, page
        )
    }

    override suspend fun getAllNotes(): List<Note> {
        return noteDaoService.getAllNotes()
    }

    override suspend fun searchNoteById(id: String): Note? {
        return noteDaoService.searchNoteById(id)
    }

    override suspend fun getNumNotes(): Int {
        return noteDaoService.getNumNotes()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    override suspend fun insertNotes(notes: List<Note>): LongArray =
        noteDaoService.insertNotes(notes)
}