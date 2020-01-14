package com.example.composesample.ui.dashboard

import android.os.Looper
import android.util.Log
import androidx.compose.Composable
import androidx.compose.Handler
import androidx.compose.frames.ModelList
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Dp
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.res.imageResource
import com.example.composesample.R
import com.example.composesample.data.placeData
import com.example.composesample.model.*
import com.example.composesample.styles.VectorImage


private val defaultHeightSpacer: Dp = 4.dp

@Composable
fun DashboardScreen() {
    VerticalScroller(modifier = Spacing(all = 8.dp)) {
        PlaceListView()
    }
}

@Composable
private fun PlaceListView() {
    Column {
        getPlaceList().forEach {
            PlaceItemView(place = it)
            HeightSpacer(height = 8.dp)
        }
    }
}

@Composable
private fun PlaceItemView(place: Place) {
    Log.i("DashboardApp", "${place.name}")
    Card(shape = RoundedCornerShape(8.dp), elevation = 4.dp) {
        Column {
            Container(modifier = Height(180.dp) wraps Expanded) {
                DrawImage(image = +imageResource(R.drawable.header))
            }
            Container {
                Text(
                        text = place.name,
                        style = (+MaterialTheme.typography()).subtitle1.copy(color = (+MaterialTheme.colors()).onSecondary),
                        maxLines = 1,
                        modifier = Spacing(left = 4.dp, right = 4.dp)
                )
            }
            HeightSpacer(height = 8.dp)
            Text(
                    text = "Address: ${place.address}",
                    style = (+MaterialTheme.typography()).caption.copy(color = (+MaterialTheme.colors()).onSecondary),
                    maxLines = 2,
                    modifier = Spacing(left = 4.dp, right = 4.dp)
            )
            HeightSpacer(height = defaultHeightSpacer)
            TextItem(string = "City: ${place.city}")
            TextItem(string = "Rating: ${place.rating}")
            Row {
                Container(modifier = Flexible(1f), alignment = Alignment.CenterRight) {
                    FavouriteButton(
                            isFavourite = getIsFavourite(place = place),
                            onClick = { toggleFavouritePlace(place) }
                    )
                    HeightSpacer(height = 10.dp)
                }
            }
            HeightSpacer(height = 8.dp)
        }
    }
}

@Composable
private fun TextItem(string: String) {
    Text(
            text = string,
            style = (+MaterialTheme.typography()).caption.copy(color = (+MaterialTheme.colors()).onSecondary),
            modifier = Spacing(left = 4.dp, right = 4.dp)
    )
    HeightSpacer(height = defaultHeightSpacer)
}

@Composable
private fun FavouriteButton(isFavourite: Boolean, onClick: (Boolean) -> Unit) {
    Ripple(bounded = false, radius = 24.dp) {
        Toggleable(value = isFavourite, onValueChange = onClick) {
            Container(modifier = Size(48.dp, 24.dp)) {
                if (isFavourite)
                    VectorImage(
                            id = R.drawable.ic_baseline_favorite_24,
                            tint = (+MaterialTheme.colors()).onSurface
                    )
                else
                    VectorImage(
                            id = R.drawable.ic_baseline_unfavorite_24,
                            tint = (+MaterialTheme.colors()).onSurface
                    )
            }
        }
    }
}

fun toggleFavouritePlace(place: Place) {
    with(FavouriteModel) {
        if (favPlaceList.contains(place)) {
            removeFavourite(place = place)
        } else {
            addFavourite(place = place)
        }
    }
}

private fun getIsFavourite(place: Place): Boolean {
    return FavouriteModel.favPlaceList.contains(place)
}