package com.example.cleanarchitecturelearn.business.interactors.notelist

import com.example.cleanarchitecturelearn.business.data.cache.CacheError
import com.example.cleanarchitecturelearn.business.data.cache.FORCE_GENERAL_FAILURE
import com.example.cleanarchitecturelearn.business.data.cache.FORCE_NEW_NOTE_EXCEPTION
import com.example.cleanarchitecturelearn.business.data.cache.abstraction.NoteCacheDataSource
import com.example.cleanarchitecturelearn.business.data.network.abstraction.NoteNetworkDataSource
import com.example.cleanarchitecturelearn.business.domain.model.NoteFactory
import com.example.cleanarchitecturelearn.business.domain.state.DataState
import com.example.cleanarchitecturelearn.business.interactors.notelist.InsertNewNote.Companion.INSERT_NOTE_SUCCESS
import com.example.cleanarchitecturelearn.di.DependencyContainer
import com.example.cleanarchitecturelearn.framework.presentation.notelist.state.NoteListStateEvent
import com.example.cleanarchitecturelearn.framework.presentation.notelist.state.NoteListViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class InsertNewNoteTest {

    //system in test
    private val insertNewNote: InsertNewNote

    //dependencies
    private val dependencyContainer: DependencyContainer = DependencyContainer()
    private val noteCacheDataSource: NoteCacheDataSource
    private val noteNetworkDataSource: NoteNetworkDataSource
    private val noteFactory: NoteFactory

    init {
        dependencyContainer.build()
        noteCacheDataSource = dependencyContainer.noteCacheDataSource
        noteNetworkDataSource = dependencyContainer.noteNetworkDataSource
        noteFactory = dependencyContainer.noteFactory
        insertNewNote = InsertNewNote(
            noteFactory = noteFactory,
            noteCacheDataSource = noteCacheDataSource,
            noteNetworkDataSource = noteNetworkDataSource
        )
    }

    @Test
    fun insertNote_success_confirmNetworkAndCacheUpdated() = runBlocking {

        val newNote = noteFactory.createSingleNote(
            id = UUID.randomUUID().toString(),
            title = UUID.randomUUID().toString()
        )

        insertNewNote.insertNewNote(
            id = newNote.id,
            title = newNote.title,
            stateEvent = NoteListStateEvent.InsertNewNoteEvent(newNote.title)
        ).collect(object: FlowCollector<DataState<NoteListViewState>?>{
            override suspend fun emit(value: DataState<NoteListViewState>?) {
                assertEquals(
                    value?.stateMessage?.response?.message,
                    INSERT_NOTE_SUCCESS
                )
            }
        })

        // confirm network was updated
        val networkNoteThatWasInserted = noteNetworkDataSource.searchNote(newNote)
        assertTrue { networkNoteThatWasInserted == newNote }

        // confirm cache was updated
        val cacheNoteThatWasInserted = noteCacheDataSource.searchNoteById(newNote.id)
        assertTrue { cacheNoteThatWasInserted == newNote }
    }

    @Test
    fun insertNote_fail_confirmNetworkAndCacheUnchanged() = runBlocking {

        val newNote = noteFactory.createSingleNote(
            id = FORCE_GENERAL_FAILURE,
            title = UUID.randomUUID().toString(),
            body = UUID.randomUUID().toString()
        )

        insertNewNote.insertNewNote(
            id = newNote.id,
            title = newNote.title,
            stateEvent = NoteListStateEvent.InsertNewNoteEvent(newNote.title)
        ).collect(object: FlowCollector<DataState<NoteListViewState>?>{
            override suspend fun emit(value: DataState<NoteListViewState>?) {
                assertEquals(
                    value?.stateMessage?.response?.message,
                    InsertNewNote.INSERT_NOTE_FAILED
                )
            }
        })

        // confirm network was not changed
        val networkNoteThatWasInserted = noteNetworkDataSource.searchNote(newNote)
        assertTrue { networkNoteThatWasInserted == null }

        // confirm cache was not changed
        val cacheNoteThatWasInserted = noteCacheDataSource.searchNoteById(newNote.id)
        assertTrue { cacheNoteThatWasInserted == null }
    }

    @Test
    fun throwException_checkGenericError_confirmNetworkAndCacheUnchanged() = runBlocking {

        val newNote = noteFactory.createSingleNote(
            id = FORCE_NEW_NOTE_EXCEPTION,
            title = UUID.randomUUID().toString(),
            body = UUID.randomUUID().toString()
        )

        insertNewNote.insertNewNote(
            id = newNote.id,
            title = newNote.title,
            stateEvent = NoteListStateEvent.InsertNewNoteEvent(newNote.title)
        ).collect(object: FlowCollector<DataState<NoteListViewState>?>{
            override suspend fun emit(value: DataState<NoteListViewState>?) {
                assert(
                    value?.stateMessage?.response?.message
                        ?.contains(CacheError.CACHE_ERROR_UNKNOWN) ?: false
                )
            }
        })

        // confirm network was not changed
        val networkNoteThatWasInserted = noteNetworkDataSource.searchNote(newNote)
        assertTrue { networkNoteThatWasInserted == null }

        // confirm cache was not changed
        val cacheNoteThatWasInserted = noteCacheDataSource.searchNoteById(newNote.id)
        assertTrue { cacheNoteThatWasInserted == null }
    }
}