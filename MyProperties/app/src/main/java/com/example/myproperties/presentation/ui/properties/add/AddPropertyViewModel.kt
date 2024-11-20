package com.example.myproperties.presentation.ui.properties.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.domain.usecases.AddPropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPropertyViewModel @Inject constructor(
    private val addPropertyUseCase: AddPropertyUseCase
): ViewModel() {

    fun addProperty(propertyModel: PropertyModel){
        viewModelScope.launch {
            addPropertyUseCase(propertyModel= propertyModel)
        }
    }
}