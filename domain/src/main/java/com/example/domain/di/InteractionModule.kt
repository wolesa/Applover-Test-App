package com.example.domain.di

import com.example.domain.interactor.LoginUseCase
import org.koin.dsl.module

val interactionModule = module {

    factory { LoginUseCase(get()) }

}