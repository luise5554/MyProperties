package com.example.myproperties.presentation.ui.properties.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
import com.example.myproperties.presentation.ui.properties.add.subviews.create.BathroomsTextField
import com.example.myproperties.presentation.ui.properties.add.subviews.create.BedsTextField
import com.example.myproperties.presentation.ui.properties.add.subviews.create.DescriptionTextField
import com.example.myproperties.presentation.ui.properties.add.subviews.create.MaxGuestTextField
import com.example.myproperties.presentation.ui.properties.add.subviews.create.MessageErrorDialog
import com.example.myproperties.presentation.ui.properties.add.subviews.create.PreviewLocationView
import com.example.myproperties.presentation.ui.properties.add.subviews.create.PropertyTypeField
import com.example.myproperties.presentation.ui.properties.add.subviews.create.SuccessDialog
import com.example.myproperties.presentation.ui.properties.add.subviews.create.TitleTextField
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.PhotoChooserView


@Composable
fun AddPropertyView(
    navController: NavHostController,
    addPropertyViewModel: AddPropertyViewModel
) {

    val savingInfo: Boolean by addPropertyViewModel.isSavingInfo.observeAsState(true)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        if (savingInfo) {
            Row (
                modifier = Modifier.fillMaxSize()
            ){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .width(100.dp)
                        .height(100.dp)
                )
            }
        } else {

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

            PhotoChooserView(addPropertyViewModel = addPropertyViewModel)

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
        SuccessDialog(addPropertyViewModel, navController)
    }


}






