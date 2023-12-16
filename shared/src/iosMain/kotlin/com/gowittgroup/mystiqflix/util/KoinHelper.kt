package com.gowittgroup.mystiqflix.util

import com.gowittgroup.mystiqflix.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin{
        modules(getSharedModules())
    }
}