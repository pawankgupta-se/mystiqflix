package com.gowittgroup.mystiqflix.android

import android.app.Application
import com.gowittgroup.mystiqflix.android.di.appModule
import com.gowittgroup.mystiqflix.di.getSharedModules
import org.koin.core.context.startKoin

class MystiqFlixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
           modules(appModule + getSharedModules())
        }
    }
}