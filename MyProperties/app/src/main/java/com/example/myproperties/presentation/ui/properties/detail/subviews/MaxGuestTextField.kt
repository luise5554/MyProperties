package com.example.myproperties.presentation.ui.properties.detail.subviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myproperties.R
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel

@Composable
fun MaxGuestTextField(addPropertyViewModel: AddPropertyViewModel) {
    val maxGuestsNumber: String by addPropertyViewModel.maxGuestsNumber.observeAsState("")

    OutlinedTextField(
        value = maxGuestsNumber,
        onValueChange = {
            addPropertyViewModel.changeMaxGuestNumber(it)
        },
        label = {
            Text(text = stringResource(id = R.string.guests_number))
        },
        modifier =
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}