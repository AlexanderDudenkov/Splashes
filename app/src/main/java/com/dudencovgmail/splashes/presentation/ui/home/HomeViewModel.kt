package com.dudencovgmail.splashes.presentation.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dudencovgmail.splashes.data.dto.responses.UserPhoto
import com.dudencovgmail.splashes.data.utils.Constants.API_KEY
import com.dudencovgmail.splashes.domain.UnsplashUserPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
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
            progress.set(true)

            unsplashUserPhotoUseCase.getPhotos(API_KEY, 1, 20)
                .catch { it.printStackTrace() }
                .collect { _list.value = it }

            progress.set(false)
        }
    }
}