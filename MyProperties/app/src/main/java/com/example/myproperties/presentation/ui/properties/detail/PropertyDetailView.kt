package com.example.myproperties.presentation.ui.properties.detail

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myproperties.R
import com.example.myproperties.domain.model.PhotoModel
import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.PhotoInViewModel
import com.example.myproperties.presentation.ui.properties.list.PropertyListItem
import java.io.File

@Composable
fun PropertyDetailView(
    navController: NavHostController,
    propertyId: String,
    propertyDetailViewModel: PropertyDetailViewModel
) {

    val propertyModel: PropertyModel? by propertyDetailViewModel.propertyModel.observeAsState()
    propertyDetailViewModel.getPropertyById(propertyId = propertyId.toInt())

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<PhotosListUiState>(
        initialValue = PhotosListUiState.Loading,
        key1 = lifecycle,
        key2 = propertyDetailViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            propertyDetailViewModel.getPhotosByPropertyId(propertyId = propertyId.toInt()).collect {
                value = it
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        if (propertyModel != null) {
            PropertyListItem(propertyModel!!, null)
        }

        when (uiState) {
            is PhotosListUiState.Error -> {}
            PhotosListUiState.Loading -> {
                CircularProgressIndicator()
            }

            is PhotosListUiState.Success -> {
                Column(Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.photos_label),
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(top = 20.dp),
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                fontSize = 18.sp
                            )
                        )

                        LazyRow {
                            items(
                                (uiState as PhotosListUiState.Success).properties,
                                key = { it.photoId }) {
                                PhotoItemForDetail(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(100.dp)
                                    , photoInViewModel= it
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}


@Composable
private fun PhotoItemForDetail(modifier: Modifier = Modifier, photoInViewModel: PhotoModel) {

    val uri = Uri.fromFile(File(photoInViewModel.localPath))

    Card(
        modifier = modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(2.dp))
                .border(
                    BorderStroke(
                        width = 5.dp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ),
            contentScale = ContentScale.Crop
        )
    }
}