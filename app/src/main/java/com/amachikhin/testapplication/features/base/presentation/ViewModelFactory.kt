package com.amachikhin.testapplication.features.base.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T ?: throw IllegalArgumentException(
            "Cannot find view model $modelClass. Did you add to your @Subcomponent's @Module:\n" +
                    "            @Binds\n" +
                    "            @IntoMap\n" +
                    "            @ViewModelKey($modelClass::class)\n" +
                    "            fun bind$modelClass(viewModel: $modelClass): ViewModel"
        )
}
