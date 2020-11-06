package com.example.composedemo.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import com.example.composedemo.domain.entity.GuideData
import com.example.composedemo.provider.GuideDataProvider

@Composable
@Preview(name = "preview")
fun primaryButton(@PreviewParameter(GuideDataProvider::class) guideData: GuideData) {
    val containerModifier = Modifier.clip(RoundedCornerShape(50))
            .wrapContentHeight()
    Box(modifier = containerModifier.padding(32.dp, 16.dp, 32.dp, 16.dp)) {
        when (guideData.guideMode) {
            1 -> {
                Text(guideData.guideTip)
            }
            2 -> {
                Button(onClick = {}) {
                    Text(text = guideData.guideTip)
                }
            }
        }
    }
}