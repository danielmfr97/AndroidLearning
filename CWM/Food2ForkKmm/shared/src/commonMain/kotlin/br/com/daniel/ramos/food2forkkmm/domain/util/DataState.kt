package br.com.daniel.ramos.food2forkkmm.domain.util

/*
* Wrapper that holds data
 */
data class DataState< T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false
) {
    // Functions that helps with states
    companion object {
        fun <T> error(message: String): DataState<T> {
            return DataState(message = message)
        }

        fun <T> data(
            message: String? = null,
            data: T? = null,
        ): DataState<T> {
            return  DataState(message = message, data = data)
        }

        fun <T> loading() = DataState<T>(isLoading = true)
    }
}