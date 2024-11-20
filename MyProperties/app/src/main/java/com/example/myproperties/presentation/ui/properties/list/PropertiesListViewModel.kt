package com.example.myproperties.presentation.ui.properties.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproperties.domain.usecases.GetPropertyUseCase
import com.example.myproperties.presentation.ui.properties.list.PropertiesListUiState.Success
import com.example.myproperties.presentation.ui.properties.list.PropertiesListUiState.Error
import com.example.myproperties.presentation.ui.properties.list.PropertiesListUiState.Loading

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PropertiesListViewModel @Inject constructor(
    private val getPropertyUseCase: GetPropertyUseCase
): ViewModel() {

    val uiState: StateFlow<PropertiesListUiState> = getPropertyUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

}