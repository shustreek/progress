package com.amachikhin.testapplication.features.base.data

import com.amachikhin.testapplication.features.base.CoroutineScopeImpl

abstract class RepositoryImpl : Repository, CoroutineScopeImpl()
