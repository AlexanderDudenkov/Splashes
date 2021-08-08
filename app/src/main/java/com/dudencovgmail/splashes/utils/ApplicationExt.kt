package com.dudencovgmail.splashes.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle

fun Application.registerActivityLifecycleCallbacks(
    onActivityCreated: (p0: Activity, p1: Bundle?) -> Unit = { _, _ -> },
    onActivityStarted: (p0: Activity) -> Unit = {},
    onActivityResumed: (p0: Activity) -> Unit = {},
    onActivityPaused: (p0: Activity) -> Unit = {},
    onActivityStopped: (p0: Activity) -> Unit = {},
    onActivitySaveInstanceState: (p0: Activity, p1: Bundle?) -> Unit = { _, _ -> },
    onActivityDestroyed: (p0: Activity) -> Unit = {},
) {
    registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            onActivityCreated(p0, p1)
        }

        override fun onActivityStarted(p0: Activity) {
            onActivityStarted(p0)
        }

        override fun onActivityResumed(p0: Activity) {
            onActivityResumed(p0)
        }

        override fun onActivityPaused(p0: Activity) {
            onActivityPaused(p0)
        }

        override fun onActivityStopped(p0: Activity) {
            onActivityStopped(p0)
        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            onActivitySaveInstanceState(p0, p1)
        }

        override fun onActivityDestroyed(p0: Activity) {
            onActivityDestroyed(p0)
        }
    })
}