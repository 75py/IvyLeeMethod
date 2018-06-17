package com.nagopy.android.ivyleemethod.ui.loading

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.nagopy.android.ivyleemethod.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoadingFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LoadingViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoadingViewModel::class.java)
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // DaggerFragmentではこっちでやっている
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.loading_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navController = NavHostFragment.findNavController(this)
        viewModel.loadTodaysTasks(navController)
    }

}
