package com.rodrigoaads.mytime

import android.app.Application
import com.rodrigoaads.mytime.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyTimeApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyTimeApplication)
            modules(
                DataModule.dataModule
            )
        }
    }
}