package com.example.composesample.ui.application

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.Container
import androidx.ui.layout.Row
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.example.composesample.R
import com.example.composesample.styles.VectorImage

@Composable
fun SampleUiComponent() {
    Row {
        Container(modifier = Flexible(1f)) {
            VectorImage(id = R.drawable.ic_baseline_face_100,
                    tint = (+MaterialTheme.colors()).onSurface)

        }
    }
}

@Preview
@Composable
fun previewSample(){
    SampleUiComponent()
}