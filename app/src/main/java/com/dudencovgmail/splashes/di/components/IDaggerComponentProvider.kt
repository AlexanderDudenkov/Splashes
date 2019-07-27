package com.dudencovgmail.splashes.di.components

import android.app.Activity

interface IDaggerComponentProvider {
    val component: IAppComponent
}

val Activity.injector get() = (application as IDaggerComponentProvider).component