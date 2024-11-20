package com.example.myproperties.presentation.ui.properties.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myproperties.R
import com.example.myproperties.presentation.ui.properties.detail.subviews.BathroomsTextField
import com.example.myproperties.presentation.ui.properties.detail.subviews.BedsTextField
import com.example.myproperties.presentation.ui.properties.detail.subviews.DescriptionTextField
import com.example.myproperties.presentation.ui.properties.detail.subviews.MaxGuestTextField
import com.example.myproperties.presentation.ui.properties.detail.subviews.PreviewLocationView
import com.example.myproperties.presentation.ui.properties.detail.subviews.PropertyTypeField
import com.example.myproperties.presentation.ui.properties.detail.subviews.TitleTextField

@Composable
fun AddPropertyView(navController: NavHostController, addPropertyViewModel: AddPropertyViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(R.string.add_property_title),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 18.sp
            )
        )

        PropertyTypeField(addPropertyViewModel = addPropertyViewModel)

        DescriptionTextField(addPropertyViewModel = addPropertyViewModel)

        TitleTextField(addPropertyViewModel = addPropertyViewModel)

        MaxGuestTextField(addPropertyViewModel = addPropertyViewModel)

        BedsTextField(addPropertyViewModel = addPropertyViewModel)

        BathroomsTextField(addPropertyViewModel = addPropertyViewModel)

        PreviewLocationView(
            navController = navController,
            addPropertyViewModel = addPropertyViewModel
        )

        Button(
            onClick = {
                addPropertyViewModel.addProperty()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }

    MessageErrorDialog(addPropertyViewModel = addPropertyViewModel)
}

@Composable
fun MessageErrorDialog(addPropertyViewModel: AddPropertyViewModel) {

    val showError: Boolean by addPropertyViewModel.showError.observeAsState(false)

    if (showError){
        AlertDialog(
            onDismissRequest = {
                addPropertyViewModel.changeVisibilityDialogState(false)
            },
            confirmButton = {
                TextButton(onClick = { addPropertyViewModel.changeVisibilityDialogState(false) }) {
                    Text(text = stringResource(id = R.string.accept))
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.title_error_dialog)
                )
            },
            text = {
                Text(text = addPropertyViewModel.errorMessage.value!!)
            }
        )
    }
}






