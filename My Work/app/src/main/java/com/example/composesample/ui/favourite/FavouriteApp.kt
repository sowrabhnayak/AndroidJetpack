package com.example.composesample.ui.favourite

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Card
import androidx.ui.material.withOpacity
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.example.composesample.R
import com.example.composesample.data.placeData
import com.example.composesample.model.FavouriteModel
import com.example.composesample.model.Place

@Composable
fun FavouriteScreen() {
    VerticalScroller(modifier = Spacing(all = 8.dp)) {
        PlaceListView()
    }
}

@Composable
private fun PlaceListView(){
    if(FavouriteModel.favPlaceList.isNullOrEmpty()){
        EmptyListTextView()
    }else{
        Column {
            FavouriteModel.favPlaceList.forEach {
                PlaceItemView(place = it)
                HeightSpacer(height = 8.dp)
            }
        }
    }
}

@Composable
private fun EmptyListTextView(){
    Row {
        Container(Flexible(1f)) {
            Text(
                    text = "There are no Favourites in your File.",
                    style = (+MaterialTheme.typography()).subtitle1
                            .copy(color = (+MaterialTheme.colors()).onSecondary)
                            .withOpacity(0.87f)
            )
        }
    }
}

@Composable
private fun PlaceItemView(place: Place){
    Card(shape = RoundedCornerShape(8.dp)) {
        Column {
            Container(modifier = Height(180.dp) wraps Expanded) {
                DrawImage(image = +imageResource(R.drawable.header))
            }
            Container {
                Text(
                        text = place.name,
                        style = (+MaterialTheme.typography()).subtitle1.copy(color = (+MaterialTheme.colors()).onSecondary),
                        maxLines = 1,
                        modifier = Spacing(left = 4.dp,right = 4.dp)
                )
            }
            HeightSpacer(height = 8.dp)
            Text(
                    text = "Address: ${place.address}",
                    style = (+MaterialTheme.typography()).caption.copy(color = (+MaterialTheme.colors()).onSecondary),
                    maxLines = 2,
                    modifier = Spacing(left = 4.dp,right = 4.dp)
            )
            HeightSpacer(height = 4.dp)
            Text(
                    text = "City: ${place.city}",
                    style = (+MaterialTheme.typography()).caption.copy(color = (+MaterialTheme.colors()).onSecondary),
                    modifier = Spacing(left = 4.dp,right = 4.dp)
            )
            HeightSpacer(height = 4.dp)
            Text(
                    text = "Rating: ${place.rating}",
                    style = (+MaterialTheme.typography()).caption.copy(color = (+MaterialTheme.colors()).onSecondary),
                    modifier = Spacing(left = 4.dp,right = 4.dp)
            )
            HeightSpacer(height = 4.dp)
        }
    }
}

@Preview
@Composable
fun FavouritePreview(){
    FavouriteModel.favPlaceList.add(placeData[0])
    FavouriteScreen()
}