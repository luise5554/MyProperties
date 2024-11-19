package com.example.myproperties.presentation.ui.properties.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myproperties.domain.model.Routes

@Composable
fun PropertiesListView(modifier: Modifier, navController: NavHostController) {
    Box(Modifier.fillMaxSize()) {

        Text(
            text = "This is the properties list",
            modifier = modifier
        )
        
        FloatingActionButton(
            onClick = {
                navController.navigate(Routes.PropertyDetail.createRoute("id:12131313"))
                      },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }
    }
}