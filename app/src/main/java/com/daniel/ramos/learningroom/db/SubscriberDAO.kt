package com.daniel.ramos.learningroom.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    /* Asynchronous queries - Querys que tém LiveData como valor de retorno, room sempre
    * rodará elas em background thread
    */
    @Query("SELECT * FROM  subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>
}