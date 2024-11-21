package com.example.myproperties.presentation.ui.properties.add.subviews.create

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.myproperties.R
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel

@Composable
fun SuccessDialog(addPropertyViewModel: AddPropertyViewModel, navHostController: NavHostController) {

    val showSuccessDialog: Boolean by addPropertyViewModel.showSuccessDialgo.observeAsState(false)

    if (showSuccessDialog){
        AlertDialog(
            onDismissRequest = {
            },
            confirmButton = {
                TextButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Text(text = stringResource(id = R.string.accept))
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.title_success_dialog)
                )
            },
            text = {
                Text(text = stringResource(id = R.string.message_success_dialog))
            }
        )
    }
}