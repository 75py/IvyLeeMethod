package com.nagopy.android.ivyleemethod.di

import androidx.lifecycle.ViewModel
import com.nagopy.android.ivyleemethod.ui.loading.LoadingFragment
import com.nagopy.android.ivyleemethod.ui.loading.LoadingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface LoadingFragmentModule {

    @ContributesAndroidInjector
    fun contributeFragment(): LoadingFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoadingViewModel::class)
    fun bindViewModel(
            viewModel: LoadingViewModel
    ): ViewModel
}
