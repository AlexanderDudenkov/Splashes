package com.dudencovgmail.splashes.repository.local

import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDB {
    val realm = Realm.getInstance(getConfig())

    private fun getConfig(): RealmConfiguration {
        val configCash = RealmConfiguration.Builder()
                .name("db1.realm")
                .build()
        Realm.setDefaultConfiguration(configCash)
        return configCash
    }
}