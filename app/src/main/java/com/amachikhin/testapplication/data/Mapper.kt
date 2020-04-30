package com.amachikhin.testapplication.data

import com.amachikhin.testapplication.data.model.ResponseModel

abstract class Mapper<From : ResponseModel, To> {
    abstract fun map(from: From): To
}
