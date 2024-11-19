package com.example.myproperties.presentation.ui.properties.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun PropertyDetailView(navController: NavHostController, propertyId: String) {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "This is the property detail view $propertyId")
    }
}