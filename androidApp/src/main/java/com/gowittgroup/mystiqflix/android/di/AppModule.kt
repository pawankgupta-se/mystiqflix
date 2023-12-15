package com.gowittgroup.mystiqflix.android.di


import com.gowittgroup.mystiqflix.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
   viewModel {
       HomeViewModel(get())
   }
}