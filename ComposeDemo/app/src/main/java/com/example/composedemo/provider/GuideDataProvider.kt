package com.example.composedemo.provider

import androidx.ui.tooling.preview.PreviewParameterProvider
import com.example.composedemo.domain.entity.GuideData

class GuideDataProvider(private val data: GuideData) : PreviewParameterProvider<GuideData> {

    override val values: Sequence<GuideData>
        get() = sequenceOf(data)

}