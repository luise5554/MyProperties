package com.example.myproperties.presentation.ui.properties.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.myproperties.R
import com.example.myproperties.domain.model.router.Routes

@Composable
fun PropertiesListView(modifier: Modifier, navController: NavHostController, propertiesListViewModel: PropertiesListViewModel) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<PropertiesListUiState>(
        initialValue = PropertiesListUiState.Loading,
        key1 = lifecycle,
        key2 = propertiesListViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            propertiesListViewModel.uiState.collect{
                value = it
            }
        }
    }

    when(uiState){
        is PropertiesListUiState.Error -> {}
        PropertiesListUiState.Loading -> {
            CircularProgressIndicator()}
        is PropertiesListUiState.Success -> {
            Box(Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.property_list_title),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(top = 20.dp, bottom = 20.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )

                    LazyColumn {
                        items( (uiState as PropertiesListUiState.Success).properties, key= {it.propertyId}){
                            Text(text = it.title + ""  + it.propertyId)
                        }
                    }
                }

                FloatingActionButton(
                    onClick = {
                        //For detail: TODO
                        //navController.navigate(Routes.PropertyDetail.createRoute("id:12131313"))
                        navController.navigate(Routes.AddProperty.route)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                }
            }
        }
    }
}
