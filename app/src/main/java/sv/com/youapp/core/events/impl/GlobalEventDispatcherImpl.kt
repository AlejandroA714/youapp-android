package sv.com.youapp.core.events.impl

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import sv.com.youapp.core.events.GlobalEvent
import sv.com.youapp.core.events.GlobalEventDispatcher

class GlobalEventDispatcherImpl() : GlobalEventDispatcher {
    private val _events = MutableSharedFlow<GlobalEvent>(
        replay = 0,
        extraBufferCapacity = 64,
    )
    override val events: SharedFlow<GlobalEvent> = _events.asSharedFlow()

    override fun emit(event: GlobalEvent) {
        _events.tryEmit(event)
    }

    override suspend fun forceEmit(event: GlobalEvent) {
        _events.emit(event)
    }
}