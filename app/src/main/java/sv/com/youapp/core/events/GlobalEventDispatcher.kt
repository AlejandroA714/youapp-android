package sv.com.youapp.core.events

import kotlinx.coroutines.flow.SharedFlow

interface GlobalEventDispatcher {
    val events: SharedFlow<GlobalEvent>
    fun emit(event: GlobalEvent)
    suspend fun forceEmit(event: GlobalEvent)
}