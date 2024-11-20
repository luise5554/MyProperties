package com.example.myproperties.presentation.ui.properties.detail.subviews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myproperties.R
import com.example.myproperties.domain.model.router.Routes
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel

@Composable
fun PreviewLocationView(
    navController: NavHostController,
    addPropertyViewModel: AddPropertyViewModel
){
    Column(
        Modifier.fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .clip(shape = RoundedCornerShape(2.dp))
            .border(BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onSurface))
    ) {
        if (addPropertyViewModel.latitude.value != null && addPropertyViewModel.longitude.value != null) {
            Column(modifier = Modifier
                .fillMaxWidth()){

                val latitude = addPropertyViewModel.latitude.value
                val longitude = addPropertyViewModel.longitude.value

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://maps.googleapis.com/maps/api/staticmap?center=$latitude,$longitude&markers=color:red%7Clabel:G%7C$latitude,$longitude&zoom=14&size=800x800&key=AIzaSyCeqgXuYGrlKMfKrKqfGCy1ZDzqzqstdPc")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                )

                Button(
                    onClick = {
                        navController.navigate(Routes.Map.route)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                ) {
                    Text(text = stringResource(id = R.string.change_location))
                }
            }
        }else{

            Button(
                onClick = {
                    navController.navigate(Routes.Map.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            ) {
                Text(text = stringResource(id = R.string.select_location))
            }

        }
    }

}