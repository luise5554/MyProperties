package com.example.myproperties.presentation.ui.properties.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.domain.usecases.GetPropertyPhotosUseCase
import com.example.myproperties.domain.usecases.GetPropertyUseCase
import com.example.myproperties.presentation.ui.properties.list.PropertiesListUiState
import com.example.myproperties.presentation.ui.properties.list.PropertiesListUiState.Error
import com.example.myproperties.presentation.ui.properties.list.PropertiesListUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val propertyUseCase: GetPropertyUseCase,
    private val getPropertyPhotosUseCase: GetPropertyPhotosUseCase
): ViewModel() {

    private val _latitude = MutableLiveData<Double?>()
    val latitude: LiveData<Double?> = _latitude

    private val _longitude = MutableLiveData<Double?>()
    val longitude: LiveData<Double?> = _longitude

    private val _propertyModel = MutableLiveData<PropertyModel>()
    val propertyModel: LiveData<PropertyModel> = _propertyModel

    fun getPropertyById(propertyId: Int){
        viewModelScope.launch(Dispatchers.IO){
            val property = propertyUseCase.getPropertyBy(propertyId = propertyId)
            viewModelScope.launch {
                _propertyModel.value = property
            }
        }
    }

    fun getPhotosByPropertyId(propertyId: Int): StateFlow<PhotosListUiState>{
        return getPropertyPhotosUseCase.getPhotosByPropertyId(propertyId).map(PhotosListUiState::Success)
            .catch { PhotosListUiState.Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PhotosListUiState.Loading)
    }

}