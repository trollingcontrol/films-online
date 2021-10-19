package com.trollingcont.filmsonline

import android.app.Application
import com.trollingcont.filmsonline.di.AppComponent
import com.trollingcont.filmsonline.di.DaggerAppComponent

open class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create()
    }
}