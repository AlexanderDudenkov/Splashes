package com.dudencovgmail.splashes.repository.local

import com.dudencovgmail.splashes.data.Model

class Cash private constructor() {

    var modelList: ArrayList<Model>? = null

    companion object {
        val instance = Cash()
    }
}
