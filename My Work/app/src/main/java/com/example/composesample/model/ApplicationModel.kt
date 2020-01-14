package com.example.composesample.model

import android.os.Looper
import androidx.compose.Handler
import androidx.compose.Model
import androidx.compose.frames.ModelList
import com.example.composesample.data.placeData


@Model
object NavigationScreen{
    var currentScreen: NavigationType = NavigationType.Dashboard
}

fun navigateTo(screen: NavigationType){
    NavigationScreen.currentScreen = screen
}

enum class NavigationType{
    Dashboard,
    Favourites,
    Profile
}

@Model
object DashboardDataModel{
    var placeList: ModelList<Place> = ModelList()
}

fun getPlaceList(): ModelList<Place>{
    if(DashboardDataModel.placeList.isNullOrEmpty()){
        DashboardDataModel.placeList.addAll(placeData)
    }
    return DashboardDataModel.placeList
}

@Model
object ProfileDialogData{
    var message: String = ""
}

@Model
object FavouriteModel{
    var favPlaceList: ModelList<Place> = ModelList()
}

fun addFavourite(place: Place){
    FavouriteModel.favPlaceList.add(place)
}

fun removeFavourite(place: Place){
    FavouriteModel.favPlaceList.remove(place)
}

