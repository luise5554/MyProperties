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
import androidx.compose.ui.unit.dp
import com.example.myproperties.R
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel

@Composable
fun TitleTextField(addPropertyViewModel: AddPropertyViewModel) {
    val title: String by addPropertyViewModel.title.observeAsState("")

    OutlinedTextField(
        value = title,
        onValueChange = {
            addPropertyViewModel.changeTitle(it)
        },
        label = {
            Text(text = stringResource(id = R.string.title))
        },
        modifier =
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )

    )
}