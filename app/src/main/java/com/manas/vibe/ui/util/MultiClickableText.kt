package com.manas.vibe.ui.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle

data class TextSegment(
    val text: String,
    val color: Color,
    val fontWeight: FontWeight? = null,
    val textDecoration: TextDecoration? = null,
    val isClickable: Boolean = false,
    val tag: String = text,
    val onClick: (() -> Unit)? = null
)

@Composable
fun MultiClickableText(
    segment: List<TextSegment>,
    modifier: Modifier = Modifier,
    baseTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    textAlign: TextAlign = TextAlign.Center
) {
    val annotatedString = buildAnnotatedString {
        segment.forEach { seg ->
            if (seg.isClickable) {
                withLink(
                    LinkAnnotation.Clickable(
                        tag = seg.tag,
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = seg.color,
                                fontWeight = seg.fontWeight,
                                textDecoration = seg.textDecoration ?: TextDecoration.Underline
                            )
                        ),
                        linkInteractionListener = {
                            seg.onClick?.invoke()
                        }
                    )
                ) {
                    append(seg.text)
                }
            } else {
                withStyle(
                    style = SpanStyle(
                        color = seg.color,
                        fontWeight = seg.fontWeight ?: FontWeight.Normal,
                        textDecoration = seg.textDecoration ?: TextDecoration.None
                    )
                ) {
                    append(seg.text)
                }
            }
        }
    }
    Text(
        text = annotatedString,
        modifier = modifier.fillMaxWidth(),
        style = baseTextStyle.copy(textAlign = textAlign)
    )
}

