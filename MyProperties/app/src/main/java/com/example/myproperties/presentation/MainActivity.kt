package com.example.myproperties.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myproperties.domain.model.router.Routes
import com.example.myproperties.infrastructure.utils.ManifestUtils
import com.example.myproperties.presentation.theme.MyPropertiesTheme
import com.example.myproperties.presentation.ui.maps.MapView
import com.example.myproperties.presentation.ui.maps.MapViewModel
import com.example.myproperties.presentation.ui.properties.add.AddPropertyView
import com.example.myproperties.presentation.ui.properties.add.AddPropertyViewModel
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.PhotoChooserView
import com.example.myproperties.presentation.ui.properties.detail.PropertyDetailView
import com.example.myproperties.presentation.ui.properties.detail.PropertyDetailViewModel
import com.example.myproperties.presentation.ui.properties.list.PropertiesListView
import com.example.myproperties.presentation.ui.properties.list.PropertiesListViewModel
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val propertiesListViewModel: PropertiesListViewModel by viewModels()
    private val addPropertyViewModel: AddPropertyViewModel by viewModels()
    private val mapViewModel: MapViewModel by viewModels()
    private val propertyDetailViewModel: PropertyDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the API key from the manifest file
        val apiKey = ManifestUtils.getApiKeyFromManifest(this)
        // Initialize the Places API with the retrieved API key
        if (!Places.isInitialized() && apiKey != null) {
            Places.initialize(applicationContext, apiKey)
        }

        setContent {
            mainView()
        }
    }

    @Composable
    fun mainView(){
        MyPropertiesTheme {
            Surface(modifier = Modifier.fillMaxSize()) {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.PropertyList.route){

                    composable(route = Routes.PropertyList.route) {
                        PropertiesListView(modifier = Modifier.padding(10.dp), navController, propertiesListViewModel)
                    }

                    composable(route = Routes.PropertyDetail.route,
                        arguments = listOf(navArgument(name = Routes.PROPERTY_ID_KEY){
                            type = NavType.StringType
                        }
                        )) { backStackEntry ->
                        val propertyId = backStackEntry.arguments?.getString(Routes.PROPERTY_ID_KEY)
                        PropertyDetailView(navController, propertyId ?: "", propertyDetailViewModel)
                    }

                    composable(route = Routes.AddProperty.route) {
                        AddPropertyView(navController, addPropertyViewModel)
                    }

                    composable(route = Routes.Map.route) {
                        MapView(mapViewModel, context = this@MainActivity, addPropertyViewModel, navController)
                    }
                }
            }
        }
    }

}


