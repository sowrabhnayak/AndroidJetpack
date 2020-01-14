package com.example.composesample.ui.application

import androidx.annotation.DrawableRes
import androidx.compose.*
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.example.composesample.R
import com.example.composesample.styles.VectorImage
import com.example.composesample.styles.appTypography
import com.example.composesample.styles.lightThemeColors
import com.example.composesample.model.NavigationScreen
import com.example.composesample.model.NavigationType
import com.example.composesample.model.navigateTo
import com.example.composesample.ui.dashboard.DashboardScreen
import com.example.composesample.ui.favourite.FavouriteScreen
import com.example.composesample.ui.profile.ProfileScreen

@Composable
fun ComposeSampleApplication() {
    val (_, onScreenChange) = +state { NavigationScreen.currentScreen }
    AppThemeDefault {
        Column {
            ApplicationToolbar(changeScreen = { onScreenChange })
            Container(modifier = Flexible(1f)) {
                AppContent()
            }
            BottomNavigationBar(changeScreen = { onScreenChange })
        }
    }
}

@Composable
private fun AppThemeDefault(composable: @Composable() () -> Unit) {
    MaterialTheme(
            colors = lightThemeColors,
            typography = appTypography,
            children = composable)
}

@Composable
private fun ApplicationToolbar(changeScreen: () -> Unit) {
    Surface(elevation = 4.dp, color = (+MaterialTheme.colors()).primary) {
        Column {
            TopAppBar(title = {
                Text(text = getToolbarTitle(currentScreen = NavigationScreen.currentScreen),
                        style = (+MaterialTheme.typography()).h6.copy(
                                color = (+MaterialTheme.colors()).surface
                        ))
            })
        }
    }
}

private fun getToolbarTitle(currentScreen: NavigationType): String {
    when (currentScreen) {
        NavigationType.Dashboard -> return "Dashboard"
        NavigationType.Profile -> return "Profile"
        NavigationType.Favourites -> return "Favourites"
    }
}

@Composable
private fun AppContent() {
    when (NavigationScreen.currentScreen) {
        NavigationType.Dashboard -> {
            DashboardScreen()
        }
        NavigationType.Favourites -> {
            FavouriteScreen()
        }
        NavigationType.Profile -> {
            Column {
                ProfileScreen(Flexible(1f))
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(changeScreen: () -> Unit) {
    Surface(elevation = 8.dp) {
        Container(modifier = Height(56.dp) wraps Expanded) {
            Row {
                Container(modifier = Flexible(1f)) {
                    NavigationAction(id = R.drawable.ic_baseline_people_24) {
                        navigateTo(NavigationType.Dashboard)
                        changeScreen()
                    }
                }
                Container(modifier = Flexible(1f)) {
                    NavigationAction(id = R.drawable.ic_baseline_bookmark_border_24) {
                        navigateTo(NavigationType.Favourites)
                        changeScreen()
                    }
                }
                Container(modifier = Flexible(1f)) {
                    NavigationAction(id = R.drawable.ic_baseline_person_pin_24) {
                        navigateTo(NavigationType.Profile)
                        changeScreen()
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigationAction(
        @DrawableRes id: Int,
        onClick: () -> Unit
) {
    Ripple(bounded = false, radius = 24.dp) {
        Clickable(onClick = onClick) {
            Container(modifier = Spacing(12.dp) wraps Size(24.dp, 24.dp)) {
                VectorImage(
                        id = id,
                        tint = (+MaterialTheme.colors()).onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun showApplicationPreview() {
    ComposeSampleApplication()
}