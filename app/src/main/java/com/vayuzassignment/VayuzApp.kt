package com.vayuzassignment

import android.app.Application
import com.vayuzassignment.data.di.appModule
import com.vayuzassignment.data.di.networkModule
import com.vayuzassignment.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VayuzApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VayuzApp)
            modules(listOf(appModule, networkModule, viewModelModule))
        }
    }
}