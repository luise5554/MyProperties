package com.example.myproperties.presentation.ui.maps

import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproperties.presentation.MyPropertiesApplication
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {

    private val _selectedLocation: MutableLiveData<LatLng> = MutableLiveData(LatLng(10.4048386, -75.5572455))
    val selectedLocation: LiveData<LatLng> = _selectedLocation

    fun selectLocation(selectedPlace: String, context: Context) {
        viewModelScope.launch {
            val geocoder = Geocoder(context)
            val addresses = withContext(Dispatchers.IO) {
                // Perform geocoding on a background thread
                geocoder.getFromLocationName(selectedPlace, 1)
            }
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                val latLng = LatLng(address.latitude, address.longitude)
                changeUserSelectedLocation(latLng)
            }

        }
    }

    fun changeUserSelectedLocation(latLng: LatLng){
        _selectedLocation.value = latLng
    }

}