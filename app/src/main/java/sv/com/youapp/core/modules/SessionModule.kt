package sv.com.youapp.core.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sv.com.youapp.core.events.GlobalEventDispatcher
import sv.com.youapp.core.events.impl.GlobalEventDispatcherImpl
import sv.com.youapp.core.session.SessionManager
import sv.com.youapp.core.session.impl.SessionManagerImpl
import sv.com.youapp.core.ui.toast.ToastService
import sv.com.youapp.core.ui.toast.impl.ToastServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionModule {
    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManagerImpl(context)
    }
    @Provides
    @Singleton
    fun provideEventDispatcher(): GlobalEventDispatcher {
        return GlobalEventDispatcherImpl()
    }

    @Provides
    @Singleton
    fun provideToastService(@ApplicationContext context: Context): ToastService {
        return ToastServiceImpl(context)
    }
}