package com.example.myproperties.presentation.ui.properties.add

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.myproperties.domain.model.PhotoModel
import com.example.myproperties.domain.model.rules.PropertyInfoValidationModel
import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.domain.rules.NumberRuleValidatorInterface
import com.example.myproperties.domain.rules.PropertyInfoValidatorInterface
import com.example.myproperties.domain.usecases.AddPhotosForPropertyUseCase
import com.example.myproperties.domain.usecases.AddPropertyUseCase
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.PhotoInViewModel
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.toPhotoModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPropertyViewModel @Inject constructor(
    private val addPropertyUseCase: AddPropertyUseCase,
    private val addPhotosForPropertyUseCase: AddPhotosForPropertyUseCase,
    private val isNumberValidator: NumberRuleValidatorInterface,
    private val propertyInfoValidator: PropertyInfoValidatorInterface,
    @ApplicationContext private val  context: Context
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

    private var _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private var _showError = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private var _photoList = MutableLiveData<List<PhotoInViewModel>>(emptyList())
    var photoList: LiveData<List<PhotoInViewModel>> = _photoList

    private var _isSavingInfo = MutableLiveData(false)
    val isSavingInfo: LiveData<Boolean> = _isSavingInfo

    private var _showSuccessDialgo = MutableLiveData(false)
    val showSuccessDialgo: LiveData<Boolean> = _showSuccessDialgo

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

    fun changeVisibilitySuccessDialogState(boolean: Boolean) {
        _showSuccessDialgo.value = boolean
    }

    fun updatePhotoList(photoList: List<PhotoInViewModel>) {
        _photoList.value = photoList
    }

    fun addProperty() {
        viewModelScope.launch {
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

                _isSavingInfo.value = true

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

                    addPropertyUseCase(propertyModel = propertyModel)

                    val newList: MutableList<PhotoModel> = arrayListOf()
                    val baseId = System.currentTimeMillis().hashCode()

                    photoList.value?.forEach { photoInViewModel ->

                        val idForPath = photoInViewModel.index + baseId
                        val input = context.contentResolver.openInputStream(photoInViewModel.uri!!)
                        val outputFile = context.filesDir.resolve("${propertyModel.propertyId}_${idForPath}_photo.jpg")
                        input?.copyTo(outputFile.outputStream())
                        val newUri = outputFile.toUri()

                        if (newUri.path != null){
                            val photoIn = photoInViewModel.toPhotoModel(propertyId= propertyModel.propertyId, newUriString= newUri.path!!)
                            newList.add(photoIn)
                        }
                    }

                    addPhotosForPropertyUseCase(newList)

                    _isSavingInfo.value = false
                    _showSuccessDialgo.value = true

            }else{
                _errorMessage.value = propertyInfoValidator.mapValidationToMessage(validation)
                _showError.value = true
            }

        }

    }
}