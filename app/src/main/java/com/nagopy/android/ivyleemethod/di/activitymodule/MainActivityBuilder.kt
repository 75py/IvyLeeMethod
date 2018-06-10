package com.nagopy.android.ivyleemethod.di.activitymodule

import com.nagopy.android.ivyleemethod.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity

}
