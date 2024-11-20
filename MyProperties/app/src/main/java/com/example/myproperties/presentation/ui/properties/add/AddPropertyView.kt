package com.example.myproperties.presentation.ui.properties.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.myproperties.domain.model.PropertyModel

@Composable
fun AddPropertyView(navController: NavHostController, addPropertyViewModel: AddPropertyViewModel) {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "This is the ADD property view", Modifier.clickable {
            addPropertyViewModel.addProperty(PropertyModel(
                propertyType = 1,
                propertyTypeName = "PropertyTypeName",
                maxRoomers = 5,
                bedsQuantity = 2,
                bathRoomsQuantity = 1,
                title = "PropertyTitle",
                description = "Description",
                latitude = 12L,
                longitude = 12L
            ))
            navController.popBackStack()
        })
    }
}