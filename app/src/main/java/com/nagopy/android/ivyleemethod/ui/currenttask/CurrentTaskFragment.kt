package com.nagopy.android.ivyleemethod.ui.currenttask

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nagopy.android.ivyleemethod.R

class CurrentTaskFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentTaskFragment()
    }

    private lateinit var viewModel: CurrentTaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.current_task_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentTaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
