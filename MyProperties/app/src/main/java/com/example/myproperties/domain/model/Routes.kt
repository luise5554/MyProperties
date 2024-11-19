package com.example.myproperties.domain.model

sealed class Routes(val route:String) {

    companion object{
        const val PROPERTY_ID_KEY = "propertyId"
    }

    data object PropertyList: Routes(route = "PropertyList")
    data object PropertyDetail: Routes(route = "PropertyDetail/{${PROPERTY_ID_KEY}}"){
        fun createRoute(propertyId: String) = "PropertyDetail/$propertyId"
    }
    data object AddProperty: Routes(route = "AddProperty")
}