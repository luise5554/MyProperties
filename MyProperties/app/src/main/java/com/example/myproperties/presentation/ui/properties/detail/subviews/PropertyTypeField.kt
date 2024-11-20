package com.example.myproperties.presentation.ui.properties.detail.subviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myproperties.R
import com.example.myproperties.domain.model.PropertyTypeModel
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel

@Composable
fun PropertyTypeField(addPropertyViewModel: AddPropertyViewModel) {

    val selectedPropertyTypeName by addPropertyViewModel.propertyTypeName.observeAsState("")
    val expanded by addPropertyViewModel.expandPropertyTypeDropdownMenu.observeAsState(false)

    val propertyTypes = listOf(
        PropertyTypeModel(name = stringResource(id = R.string.property_type_apartment)),
        PropertyTypeModel(name = stringResource(id = R.string.property_type_house)),
        PropertyTypeModel(name = stringResource(id = R.string.property_type_penthouse)),
        PropertyTypeModel(name = stringResource(id = R.string.property_type_condominium))
    )

    Column(
    ) {

        OutlinedTextField(
            value = selectedPropertyTypeName,
            onValueChange = {
                addPropertyViewModel.changeBathroomsNumber(it)
            },
            label = {
                Text(text = stringResource(id = R.string.property_type))
            },
            modifier =
            Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .clickable {
                    addPropertyViewModel.changePropertyTypeState(true)
                }
            ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = false,
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = Color.Transparent,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { addPropertyViewModel.changePropertyTypeState(false) }) {
            propertyTypes.forEach{ propertyType ->

                DropdownMenuItem(
                    text = {
                        Text(text = propertyType.name)
                    },
                    onClick = {
                        addPropertyViewModel.changePropertyTypeState(false)
                        addPropertyViewModel.changePropertyTypeName(propertyType.name)
                        addPropertyViewModel.changePropertyTypeId(propertyType.id.toString())
                    }
                )
            }
        }
    }
}