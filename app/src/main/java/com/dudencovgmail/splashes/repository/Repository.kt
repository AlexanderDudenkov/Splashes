package com.dudencovgmail.splashes.repository

import com.dudencovgmail.splashes.repository.local.ILocalRepo
import com.dudencovgmail.splashes.repository.remote.IRemoteRepo
import javax.inject.Inject

class Repository : IRepository {
    @Inject
    override lateinit var remoteRepo: IRemoteRepo
    @Inject
    override lateinit var localRepo: ILocalRepo
}
