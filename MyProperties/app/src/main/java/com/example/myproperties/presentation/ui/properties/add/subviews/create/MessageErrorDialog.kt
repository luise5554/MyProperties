package com.example.myproperties.presentation.ui.properties.add.subviews.create

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.example.myproperties.R
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel

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