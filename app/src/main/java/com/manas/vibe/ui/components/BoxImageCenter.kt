package com.manas.vibe.ui.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.manas.vibe.R

@Composable
fun BoxImgCenter(
    @DrawableRes centerImage: Int,
    modifier: Modifier = Modifier,
    @ColorRes backgroundColorRes: Int = R.color.FFFFFF,
    cornerRadius: Dp = 8.dp,
    contentDescription: String? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(colorResource(backgroundColorRes)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = centerImage),
            contentDescription = contentDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BoxImgCenterPreview() {
    BoxImgCenter(
        centerImage = R.drawable.icon_arrow_left,
        modifier = Modifier.size(48.dp)
    )
}
