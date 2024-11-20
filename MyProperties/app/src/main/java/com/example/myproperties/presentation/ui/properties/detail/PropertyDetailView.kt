package com.example.myproperties.presentation.ui.properties.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PropertyDetailView(navController: NavHostController, propertyId: String, propertyDetailViewModel: PropertyDetailViewModel) {

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Text(text = "This is the property detail view $propertyId")

            if (propertyDetailViewModel.latitude.value != null && propertyDetailViewModel.longitude.value != null){

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://maps.googleapis.com/maps/api/staticmap?center=6.25184,-75.56359&markers=color:red%7Clabel:G%7C${propertyDetailViewModel.latitude},${propertyDetailViewModel.longitude}&zoom=14&size=400x400&key=AIzaSyCeqgXuYGrlKMfKrKqfGCy1ZDzqzqstdPc")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )

            }
        }
    }
}