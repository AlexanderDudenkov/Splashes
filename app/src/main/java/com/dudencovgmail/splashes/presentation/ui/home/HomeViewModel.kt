package com.dudencovgmail.splashes.presentation.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dudencovgmail.splashes.data.local.dto.UserPhoto
import com.dudencovgmail.splashes.data.remote.utils.Constants.API_KEY
import com.dudencovgmail.splashes.domain.UnsplashUserPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val unsplashUserPhotoUseCase: UnsplashUserPhotoUseCase
) : ViewModel() {

    val progress: ObservableField<Boolean> = ObservableField()

    private val _list = MutableStateFlow<List<UserPhoto>>(emptyList())
    val list: StateFlow<List<UserPhoto>> = _list

    fun getData() {
        viewModelScope.launch(Dispatchers.Main) {
            unsplashUserPhotoUseCase.getPhotos(API_KEY, 1, 20)
                .onStart { progress.set(true) }
                .catch {
                    progress.set(false)
                    it.printStackTrace()
                }
                .collect {
                    progress.set(false)
                    _list.value = it
                }
        }
    }
}
