package com.example.myproperties.presentation.ui.properties.detail

import com.example.myproperties.domain.model.PhotoModel

sealed interface PhotosListUiState {
    object Loading: PhotosListUiState
    data class Error(val throwable: Throwable): PhotosListUiState
    data class Success(val properties: List<PhotoModel>): PhotosListUiState
}