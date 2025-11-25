package app.motion.android

import android.app.Application
import app.motion.android.di.repositoryModule
import app.motion.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MotionApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MotionApplication)
            modules(repositoryModule, viewModelModule)
        }
    }
}