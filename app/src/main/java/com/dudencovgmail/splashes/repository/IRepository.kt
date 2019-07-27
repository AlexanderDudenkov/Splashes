package com.dudencovgmail.splashes.repository

import com.dudencovgmail.splashes.repository.local.ILocalRepo
import com.dudencovgmail.splashes.repository.remote.IRemoteRepo

interface IRepository {
    val localRepo: ILocalRepo
    val remoteRepo: IRemoteRepo
}
