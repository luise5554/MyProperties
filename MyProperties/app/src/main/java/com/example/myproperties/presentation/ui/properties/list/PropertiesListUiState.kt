package com.example.myproperties.presentation.ui.properties.list

import com.example.myproperties.domain.model.PropertyModel

sealed interface PropertiesListUiState {
    object Loading: PropertiesListUiState
    data class Error(val throwable: Throwable): PropertiesListUiState
    data class Success(val properties: List<PropertyModel>): PropertiesListUiState
}