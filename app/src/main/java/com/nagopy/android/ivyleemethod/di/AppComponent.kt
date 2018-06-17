package com.example.nagopy.ivyleemethod.di

import android.app.Application
import com.nagopy.android.ivyleemethod.App
import com.nagopy.android.ivyleemethod.data.DatabaseModule
import com.nagopy.android.ivyleemethod.di.AppModule
import com.nagopy.android.ivyleemethod.di.LoadingFragmentModule
import com.nagopy.android.ivyleemethod.di.ViewModelModule
import com.nagopy.android.ivyleemethod.di.activitymodule.MainActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class
    , AppModule::class
    , DatabaseModule::class
    , ViewModelModule::class
    , MainActivityBuilder::class
    , LoadingFragmentModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun databaseModule(databaseModule: DatabaseModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)

}
