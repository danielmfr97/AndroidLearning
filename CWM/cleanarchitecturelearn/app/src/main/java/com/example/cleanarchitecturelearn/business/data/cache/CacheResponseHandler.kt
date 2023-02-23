package com.example.cleanarchitecturelearn.business.data.cache

import com.example.cleanarchitecturelearn.business.data.cache.CacheError.CACHE_DATA_NULL
import com.example.cleanarchitecturelearn.business.domain.state.*

abstract class CacheResponseHandler<ViewState, Data>(
    private val response: CacheResult<Data?>, private val stateEvent: StateEvent?
) {

    suspend fun getResult(): DataState<ViewState> {
        return when (response) {
            is CacheResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\n Reason: ${response.errorMessage}",
                        uiComponentType = UIComponentType.Dialog(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            is CacheResult.Success -> {
                if (response.value == null) {
                    DataState.error(
                        response = Response(
                            message = "${stateEvent?.errorInfo()}\n\n Reason: $CACHE_DATA_NULL",
                            uiComponentType = UIComponentType.Dialog(),
                            messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                    )
                } else {
                    handlerSuccess(resultObj = response.value)
                }
            }
        }
    }

    abstract fun handlerSuccess(resultObj: Data): DataState<ViewState>
}