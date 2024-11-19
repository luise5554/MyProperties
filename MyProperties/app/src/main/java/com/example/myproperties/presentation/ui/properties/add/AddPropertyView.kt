package com.example.myproperties.presentation.ui.properties.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun AddPropertyView(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "This is the ADD property view")
    }
}