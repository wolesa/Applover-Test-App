package com.example.applovertestapp

import android.app.Application
import com.example.applovertestapp.di.viewModelModule
import com.example.data.di.dataModule
import com.example.domain.di.interactionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application(){

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin(){
        startKoin{
            androidContext(this@BaseApp)

            modules(
                listOf(
                    viewModelModule,
                    interactionModule,
                    dataModule
                )
            )
        }
    }
}