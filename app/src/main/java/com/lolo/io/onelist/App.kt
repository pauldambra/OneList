package com.lolo.io.onelist

import android.app.Application
import com.lolo.io.onelist.core.data.di.appModule
import com.lolo.io.onelist.feature.lists.di.listsModule
import com.lolo.io.onelist.feature.settings.di.settingsModule
import com.posthog.android.PostHogAndroid
import com.posthog.android.PostHogAndroidConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class App : Application() {


    companion object {
        const val POSTHOG_API_KEY = "phc_pQ70jJhZKHRvDIL5ruOErnPy6xiAiWCqlL4ayELj4X8"
    }

    override fun onCreate() {
        super.onCreate()

        val config = PostHogAndroidConfig(POSTHOG_API_KEY).apply {
            // sessionReplay is disabled by default
            sessionReplay = true
            // sessionReplayConfig is optional, they are enabled by default
            sessionReplayConfig.maskAllTextInputs = true
            sessionReplayConfig.maskAllImages = false
            sessionReplayConfig.captureLogcat = true
            debug=true
        }
        PostHogAndroid.setup(applicationContext, config)

        startKoin {
            androidContext(this@App)
            fragmentFactory()
            modules(appModule, listsModule, settingsModule)
        }
    }
}