package com.vayuzassignment.data.di

import com.vayuzassignment.data.viewmodels.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}