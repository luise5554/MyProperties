package com.example.myproperties.presentation.ui.properties.add.subviews.photos

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

@Composable
fun PhotoChooserView(maxSelectionCount: Int = 1) {
    var selectedImages by remember {
        mutableStateOf<List<PhotoInViewModel>>(emptyList())
    }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {  uris ->
            var count = 0
            selectedImages = uris.map { uri ->
                PhotoInViewModel(index = count++, uri = uri, localPath = "")
            }

        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            multiplePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text("Select photos")
        }

        val stateList = rememberLazyListState()

        var draggingItemIndex: Int? by remember {
            mutableStateOf(null)
        }

        var delta: Float by remember {
            mutableFloatStateOf(0f)
        }

        var draggingItem: LazyListItemInfo? by remember {
            mutableStateOf(null)
        }

        val onMove = { fromIndex: Int, toIndex: Int ->
            selectedImages = selectedImages.toMutableList().apply { add(toIndex, removeAt(fromIndex)) }
        }

        LazyRow(
            modifier = Modifier
                .pointerInput(key1 = stateList) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = { offset ->
                            stateList.layoutInfo.visibleItemsInfo
                                .firstOrNull { item -> offset.x.toInt() in item.offset..(item.offset + item.size) }
                                ?.also {
                                    (it.contentType as? PhotoInViewModel)?.let { photoItem ->
                                        draggingItem = it
                                        draggingItemIndex = photoItem.index
                                    }
                                }
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            delta += dragAmount.x

                            val currentDraggingItemIndex =
                                draggingItemIndex ?: return@detectDragGesturesAfterLongPress
                            val currentDraggingItem =
                                draggingItem ?: return@detectDragGesturesAfterLongPress

                            val startOffset = currentDraggingItem.offset + delta
                            val endOffset =
                                currentDraggingItem.offset + currentDraggingItem.size + delta
                            val middleOffset = startOffset + (endOffset - startOffset) / 2

                            val targetItem =
                                stateList.layoutInfo.visibleItemsInfo.find { item ->
                                    middleOffset.toInt() in item.offset..item.offset + item.size &&
                                            currentDraggingItem.index != item.index &&
                                            item.contentType is PhotoInViewModel
                                }

                            if (targetItem != null) {
                                val targetIndex = (targetItem.contentType as PhotoInViewModel).index
                                onMove(currentDraggingItemIndex, targetIndex)
                                draggingItemIndex = targetIndex
                                delta += currentDraggingItem.offset - targetItem.offset
                                draggingItem = targetItem
                            }
                        },
                        onDragEnd = {
                            draggingItem = null
                            draggingItemIndex = null
                            delta = 0f
                        },
                        onDragCancel = {
                            draggingItem = null
                            draggingItemIndex = null
                            delta = 0f
                        },
                    )
                },
            state = stateList,
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            itemsIndexed(
                items = selectedImages,
                contentType = { index, itemT -> PhotoInViewModel(index = index, localPath = "", uri = itemT.uri) }) { index, item ->
                val modifier = if (draggingItemIndex == index) {
                    Modifier
                        .zIndex(1f)
                        .graphicsLayer {
                            translationX = delta
                        }
                } else {
                    Modifier
                }
                Item(
                    modifier = modifier.height(100.dp).width(100.dp),
                    photoInViewModel = item,
                )
            }

        }
    }
}


@Composable
private fun Item(modifier: Modifier = Modifier, photoInViewModel: PhotoInViewModel) {
    Card(
        modifier = modifier
    ) {
        AsyncImage(
            model = photoInViewModel.uri,
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
