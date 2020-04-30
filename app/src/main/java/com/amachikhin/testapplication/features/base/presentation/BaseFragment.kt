package com.amachikhin.testapplication.features.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.amachikhin.testapplication.App
import com.amachikhin.testapplication.di.AppComponent
import com.amachikhin.testapplication.features.base.domain.Interactor
import com.amachikhin.testapplication.features.base.domain.InteractorOut

abstract class BaseFragment<T : BaseViewModel<out Interactor<out InteractorOut>>> : Fragment() {

    @get:LayoutRes
    protected abstract val layout: Int

    protected abstract val viewModel: T

    abstract fun injectDependencies(appComponent: AppComponent)
    abstract fun initView(view: View)
    abstract fun initObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies((requireActivity().application as App).appComponent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        initObservers()
    }

    override fun onStart() {
        super.onStart()

        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()

        viewModel.onStop()
    }

}
