package com.dudencovgmail.splashes.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import com.dudencovgmail.splashes.data.local.dto.UserPhoto
import com.dudencovgmail.splashes.data.local.utils.json_serializers.userPhotos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserPhotosStore(context: Context): DataStore<ArrayList<UserPhoto>> = context.userPhotos
}