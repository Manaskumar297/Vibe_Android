package com.manas.vibe.ui.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.manas.vibe.R

@Composable
fun BoxTextCenter(
    @ColorRes borderColor: Int = R.color._1E3C72,
    borderWidth: Dp = 0.dp,
    @ColorRes backgroundColor: Int = R.color._1E3C72,
    @ColorRes textColor: Int = R.color.FFFFFF,
    cornerRadios: Dp = 0.dp,
    textPaddingValues: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
    text: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(cornerRadios)
    val containerModifier = modifier
        .then(
            if (borderWidth > 0.dp) {
                Modifier.border(borderWidth, colorResource(borderColor), shape)
            } else Modifier
        )
        .clip(shape)
        .background(colorResource(backgroundColor))
        .clickable { onClick() }

    Box(
        modifier = containerModifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = textStyle,
            color = colorResource(textColor),
            modifier = Modifier.padding(textPaddingValues)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BoxTextCenterPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Primary Solid Button
        BoxTextCenter(
            text = "Primary Button",
            cornerRadios = 16.dp,
            backgroundColor = R.color._1E3C72,
            textColor = R.color.FFFFFF,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        // 2. Outlined Button with Border
        BoxTextCenter(
            text = "Outlined Button",
            cornerRadios = 16.dp,
            backgroundColor = R.color.FFFFFF,
            borderColor = R.color._1E3C72,
            borderWidth = 1.5.dp,
            textColor = R.color._1E3C72,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        // 3. Compact Pill Button
        BoxTextCenter(
            text = "Compact Pill",
            cornerRadios = 24.dp,
            textPaddingValues = PaddingValues(horizontal = 24.dp, vertical = 10.dp),
            backgroundColor = R.color._1E3C72,
            textColor = R.color.FFFFFF,
            onClick = {}
        )
    }
}