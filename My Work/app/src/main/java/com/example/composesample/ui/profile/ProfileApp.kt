package com.example.composesample.ui.profile

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.tooling.preview.Preview
import com.example.composesample.R
import com.example.composesample.model.ProfileDialogData
import com.example.composesample.styles.VectorImage

@Composable
fun ProfileScreen(modifier: Modifier = Modifier.None) {

    var showDialog by +state{ false }

    if(showDialog){
        DialogAction{
            showDialog = false
        }
    }

    VerticalScroller(modifier = modifier) {
        Column(modifier = Spacing(left = 8.dp, right = 8.dp)) {
            HeightSpacer(height = 16.dp)
            profilePicture()
            HeightSpacer(height = 8.dp)
            ProfileDetails()
            ProfileDescription()
            HeightSpacer(height = 16.dp)
            RandomDetails(){
                showDialog = true
            }
        }
    }
}

@Composable
private fun profilePicture(){
    Row {
        Container(modifier = Flexible(1f)) {
                Clip(shape = CircleShape) {
                    VectorImage(id = R.drawable.ic_baseline_face_100,
                            tint = (+MaterialTheme.colors()).onSurface)
                }
        }
    }
}

@Composable
private fun ProfileDetails(){
    Row {
        Container(modifier = Flexible(1f)) {
            Text(
                    text = "Nayak Sowrabh",
                    style = (+MaterialTheme.typography()).subtitle1.copy(color = (+MaterialTheme.colors()).onSecondary))
        }
    }
}

@Composable
private fun ProfileDescription(){
    Row {
        Container(modifier = Flexible(1f)) {
            Text(text = "Madiwala, Bangalore",
                    style = (+MaterialTheme.typography()).overline.copy(color = (+MaterialTheme.colors()).onSecondary))
        }
    }
}

@Composable
private fun RandomDetails(onClick: () -> Unit){
    Column {
        for (i in 0 until 100){
            Divider(color = (+MaterialTheme.colors()).onSecondary)
            HeightSpacer(height = 8.dp)
            ItemWithAction(id = i){
                ProfileDialogData.message = getDialogString(id = i)
                onClick()
            }
            HeightSpacer(height = 8.dp)
        }
    }
}

private fun getDialogString(id: Int): String{
    return "You have clicked on detail item number $id"
}

@Composable
private fun ItemWithAction(id:Int,
                           onClick: () -> Unit){
    Clickable(onClick = onClick) {
        Text(text = "Detail Number : $id",
                style = (+MaterialTheme.typography()).caption.copy(color = (+MaterialTheme.colors()).onSecondary))
    }
}

@Composable
private fun DialogAction(onClick: () -> Unit){
    AlertDialog(
            onCloseRequest = onClick,
            text = {
                Text(
                        text = ProfileDialogData.message,
                        style = (+MaterialTheme.typography()).subtitle2
                )
            },
            confirmButton = {
                Button(
                        text = "CLOSE",
                        style = ContainedButtonStyle(color = (+MaterialTheme.colors()).background),
                        onClick = onClick
                )
            })
}

@Preview
@Composable
fun showProfilePreview(){
    ProfileScreen()
}
