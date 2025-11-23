package app.motion.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.motion.android.di.repositoryModule
import app.motion.android.di.viewModelModule
import app.motion.android.ui.AppNavHost
import app.motion.android.ui.theme.MotionAppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(repositoryModule, viewModelModule)
        }
        enableEdgeToEdge()
        setContent {
            MotionAppTheme(darkTheme = false) {
                AppNavHost()
            }
        }
    }
}