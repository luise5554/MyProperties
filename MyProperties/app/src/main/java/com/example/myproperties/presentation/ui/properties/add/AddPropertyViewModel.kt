package com.example.myproperties.presentation.ui.properties.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproperties.domain.model.rules.PropertyInfoValidationModel
import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.domain.rules.NumberRuleValidatorInterface
import com.example.myproperties.domain.rules.PropertyInfoValidatorInterface
import com.example.myproperties.domain.usecases.AddPropertyUseCase
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.PhotoInViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPropertyViewModel @Inject constructor(
    private val addPropertyUseCase: AddPropertyUseCase,
    private val isNumberValidator: NumberRuleValidatorInterface,
    private val propertyInfoValidator: PropertyInfoValidatorInterface
) : ViewModel() {

    private var _latitude = MutableLiveData<Double?>()
    val latitude: LiveData<Double?> = _latitude

    private val _longitude = MutableLiveData<Double?>()
    val longitude: LiveData<Double?> = _longitude

    private val _title = MutableLiveData("")
    var title: LiveData<String> = _title

    private val _description = MutableLiveData("")
    var description: LiveData<String> = _description

    private val _maxGuestsNumber = MutableLiveData("")
    var maxGuestsNumber: LiveData<String> = _maxGuestsNumber

    private val _bedsInProperty = MutableLiveData("")
    var bedsInProperty: LiveData<String> = _bedsInProperty

    private val _bathroomsInProperty = MutableLiveData("")
    var bathroomsInProperty: LiveData<String> = _bathroomsInProperty

    private val _propertyTypeName = MutableLiveData("")
    var propertyTypeName: LiveData<String> = _propertyTypeName

    private val _propertyTypeId = MutableLiveData("")
    private var propertyTypeId: LiveData<String> = _propertyTypeId

    private val _expandPropertyTypeDropdownMenu = MutableLiveData(false)
    var expandPropertyTypeDropdownMenu: LiveData<Boolean> = _expandPropertyTypeDropdownMenu

    private var _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> = _errorMessage

    private var _showError = MutableLiveData<Boolean>(false)
    val showError: LiveData<Boolean> = _showError

    private var _photoList = MutableLiveData<List<PhotoInViewModel>>(emptyList())
    var photoList: LiveData<List<PhotoInViewModel>> = _photoList

    fun updateLatLong(latLng: LatLng) {
        _latitude.value = latLng.latitude
        _longitude.value = latLng.longitude
    }

    fun changeTitle(string: String) {
        _title.value = string
    }

    fun changeDescription(string: String) {
        _description.value = string
    }

    fun changeMaxGuestNumber(string: String) {
        if (isNumberValidator.isNumberOrEmpty(string)) {
            _maxGuestsNumber.value = string
        }
    }

    fun changeBedsNumber(string: String) {
        if (isNumberValidator.isNumberOrEmpty(string)) {
            _bedsInProperty.value = string
        }
    }

    fun changeBathroomsNumber(string: String) {
        if (isNumberValidator.isNumberOrEmpty(string)) {
            _bathroomsInProperty.value = string
        }
    }

    fun changePropertyTypeName(string: String) {
        _propertyTypeName.value = string
    }

    fun changePropertyTypeId(string: String) {
        _propertyTypeId.value = string
    }

    fun changePropertyTypeState(boolean: Boolean) {
        _expandPropertyTypeDropdownMenu.value = boolean
    }

    fun changeVisibilityDialogState(boolean: Boolean) {
        _showError.value = boolean
    }

    fun updatePhotoList(photoList: List<PhotoInViewModel>) {
        _photoList.value = photoList
    }

    fun addProperty() {

        val validation = propertyInfoValidator.validateInfo(
            propertyType = propertyTypeId.value,
            propertyTypeName = propertyTypeName.value,
            maxGuestNumber = maxGuestsNumber.value,
            bedsQuantity = bedsInProperty.value,
            bathRoomsQuantity = bathroomsInProperty.value,
            title = title.value,
            description = description.value,
            latitude = latitude.value,
            longitude = longitude.value,
            listPhotos = photoList.value
        )

        if (validation == PropertyInfoValidationModel.SUCCESS_VALIDATION){
            val propertyModel = PropertyModel(
                propertyType = propertyTypeId.value!!.toInt(),
                propertyTypeName = propertyTypeName.value!!,
                maxGuestsNumber = maxGuestsNumber.value!!.toInt(),
                bedsQuantity = bedsInProperty.value!!.toInt(),
                bathRoomsQuantity = bathroomsInProperty.value!!.toInt(),
                title = title.value!!,
                description = description.value!!,
                latitude = latitude.value!!,
                longitude = longitude.value!!,
            )
            viewModelScope.launch {
                addPropertyUseCase(propertyModel = propertyModel)
            }
        }else{
            _errorMessage.value = propertyInfoValidator.mapValidationToMessage(validation)
            _showError.value = true
        }
    }
}