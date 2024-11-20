package com.example.myproperties.presentation.ui.maps

import android.R
import android.content.Context
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavHostController
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.maps.android.compose.DragState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapView(
    mapViewModel: MapViewModel,
    context: Context,
    addPropertyViewModel: AddPropertyViewModel,
    navController: NavHostController
) {

    val selectedLocation: LatLng by mapViewModel.selectedLocation.observeAsState(LatLng(10.4048386, -75.5572455))

    val markerState = rememberMarkerState(position = selectedLocation)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedLocation, 12f)
    }

    cameraPositionState.position = CameraPosition.fromLatLngZoom(selectedLocation, 10f)
    markerState.position = selectedLocation

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(18.dp))

        SearchBar(
            onPlaceSelected = { place ->
                mapViewModel.selectLocation(place, context)
            }
        )

        GoogleMap(
            modifier = Modifier.weight(1F),
            cameraPositionState = cameraPositionState
        ){

            Marker(
                state = markerState,
                title = "Selected Location",
                snippet = "This is the place you selected.",
                draggable = true
            )

            when (markerState.dragState) {
                DragState.DRAG -> {
                }

                DragState.START -> {}
                DragState.END -> {
                    mapViewModel.changeUserSelectedLocation(markerState.position)
                }
            }
        }

        Button(
            onClick = {
                mapViewModel.selectedLocation.value?.let { addPropertyViewModel.updateLatLong(it) }
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(text = stringResource(id = com.example.myproperties.R.string.save))
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onPlaceSelected: (String) -> Unit
) {
    // Determine the appropriate text color based on the current theme
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    // Use AndroidView to integrate an Android View (AutoCompleteTextView) into Jetpack Compose
    AndroidView(
        factory = { context ->
            AutoCompleteTextView(context).apply {
                hint = "Search for a place" // Set the hint text for the search bar

                // Set the text color and hint text color based on the current theme
                setTextColor(textColor.toArgb())
                setHintTextColor(textColor.copy(alpha = 0.6f).toArgb()) // Set hint text color with some transparency

                // Set the layout params to ensure the view takes up the full width
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // Initialize the Places API and the Autocomplete Adapter
                val autocompleteAdapter = ArrayAdapter<String>(context, R.layout.simple_dropdown_item_1line)
                val placesClient = Places.createClient(context)
                val autocompleteSessionToken = AutocompleteSessionToken.newInstance()

                // Add a text change listener to capture and handle the user's input
                addTextChangedListener { editable ->
                    val query = editable?.toString() ?: "" // Get the user's input as a query string
                    if (query.isNotEmpty()) {
                        // Build a request to fetch autocomplete predictions based on the user's input
                        val request = FindAutocompletePredictionsRequest.builder()
                            .setSessionToken(autocompleteSessionToken)
                            .setQuery(query)
                            .build()

                        // Fetch autocomplete predictions from the Places API
                        placesClient.findAutocompletePredictions(request).addOnSuccessListener { response ->
                            autocompleteAdapter.clear() // Clear the previous suggestions
                            response.autocompletePredictions.forEach { prediction ->
                                // Add each prediction to the adapter for displaying in the dropdown
                                autocompleteAdapter.add(prediction.getFullText(null).toString())
                            }
                            autocompleteAdapter.notifyDataSetChanged() // Notify the adapter to update the dropdown
                        }
                    }
                }

                // Set the adapter to the AutoCompleteTextView to display suggestions
                setAdapter(autocompleteAdapter)

                // Set an item click listener to handle when the user selects a suggestion
                setOnItemClickListener { _, _, position, _ ->
                    val selectedPlace = autocompleteAdapter.getItem(position) ?: return@setOnItemClickListener

                    // Call the callback function to handle the selected place
                    onPlaceSelected(selectedPlace)
                }
            }
        },
        modifier = modifier // Apply the passed modifier to the AutoCompleteTextView
            .fillMaxWidth() // Make sure the composable fills the maximum width available
            .padding(16.dp) //  Add padding to the search bar
    )
}