package com.example.composesample.ui.progressBar

import android.os.Looper
import androidx.annotation.CheckResult
import androidx.compose.*
import androidx.ui.core.*
import androidx.ui.engine.geometry.Rect
import androidx.ui.foundation.DeterminateProgressIndicator
import androidx.ui.graphics.*
import androidx.ui.layout.Container
import androidx.ui.layout.Expanded
import androidx.ui.layout.Padding
import androidx.ui.layout.Wrap
import androidx.ui.material.MaterialTheme

@Composable
fun RotatingCircularProgressbar() {
    Container(modifier = Expanded) {
        CircularProgressBar()
    }
}

@Composable
fun CircularProgressBar(color: Color = (+MaterialTheme.colors()).primary) {
    val progress = +state { 0f }
    DeterminateProgressIndicator(progress = progress.value) {
        CircularIndicatorContainer {
            val paint = +paint(color, StrokeCap.butt)
            Draw { canvas, parentSize ->
                val startAngle = (270f + progress.value * 360f) % 360f
                val sweep = 90f
                drawDeterminateCircularIndicator(
                        canvas,
                        parentSize,
                        startAngle,
                        sweep,
                        paint
                )
            }
        }
    }
    Handler(Looper.getMainLooper()).handler.postDelayed({ progress.value = (progress.value + 0.025f) % 1f }, 1)
}

fun drawDeterminateCircularIndicator(canvas: Canvas,
                                     parentSize: PxSize,
                                     startAngle: Float,
                                     endAngle: Float,
                                     paint: Paint) {
    val diameter = parentSize.width.value

    val diameterOffset = paint.strokeWidth / 2

    val left = diameterOffset
    val right = diameter - diameterOffset

    val top = diameterOffset
    val bottom = diameter - diameterOffset

    val rect = Rect.fromLTRB(left, top, right, bottom)
    canvas.drawArc(rect, startAngle, endAngle, false, paint)
}

@CheckResult(suggest = "+")
fun paint(color: Color, strokeCap: StrokeCap) = effectOf<Paint> {
    val basePaint = withDensity(+ambientDensity()) {
        +memo {
            Paint().apply {
                isAntiAlias = true
                style = PaintingStyle.stroke
                this.strokeWidth = 4.dp.toPx().value
            }
        }
    }
    basePaint.color = color
    basePaint.strokeCap = strokeCap
    basePaint
}

@Composable
fun CircularIndicatorContainer(children: @Composable() () -> Unit) {
    Wrap {
        Padding(padding = 8.dp) {
            Container(width = 40.dp,
                    height = 40.dp,
                    children = children)
        }
    }
}