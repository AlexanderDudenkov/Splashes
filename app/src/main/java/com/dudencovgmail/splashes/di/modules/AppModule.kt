package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.di.scopes.ApplicationScope
import com.dudencovgmail.splashes.domain.IModelListBuilder
import com.dudencovgmail.splashes.domain.IUseCases
import com.dudencovgmail.splashes.domain.Interactor
import com.dudencovgmail.splashes.repository.remote.IApiService
import com.dudencovgmail.splashes.repository.IRepository
import com.dudencovgmail.splashes.repository.Repository
import com.dudencovgmail.splashes.repository.local.IDb
import com.dudencovgmail.splashes.repository.local.ILocalRepo
import com.dudencovgmail.splashes.repository.local.LocalRepo
import com.dudencovgmail.splashes.repository.remote.IRemoteRepo
import com.dudencovgmail.splashes.repository.remote.RemoteRepo
import dagger.Module
import dagger.Provides

@Module(includes = [NetModule::class, DbModule::class, MapperModule::class])
class AppModule {

    @Provides
    @ApplicationScope
    fun provideIUseCases(repository: IRepository, mapper: IModelListBuilder): IUseCases =
            Interactor(repository, mapper)

    @Provides
    @ApplicationScope
    fun provideIRepository(local: ILocalRepo, remote: IRemoteRepo): IRepository =
            Repository().apply {
                localRepo = local
                remoteRepo = remote
            }

    @Provides
    @ApplicationScope
    fun provideILocalRepo(db: IDb): ILocalRepo = LocalRepo().apply { this.db = db }

    @Provides
    @ApplicationScope
    fun provideIRemoteRepo(apiService: IApiService): IRemoteRepo = RemoteRepo().apply {
        this.apiService = apiService
    }
}