package com.example.applovertestapp.di

import com.example.applovertestapp.viewModel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }

}