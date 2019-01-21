package com.dudencovgmail.splashes.repository.local

import com.dudencovgmail.splashes.data.Model

interface ILocal {
    fun readModelList(): ArrayList<Model>
    fun writeModel(model: Model)
    fun writeModelList(list: ArrayList<Model>)
}