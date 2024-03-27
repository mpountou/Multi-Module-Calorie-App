package com.intelligent.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.intelligent.ui.LocalSpacing


@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(
        size = LocalSpacing.current.extraLarge
    ),
    text: String,
    textColor: Color = MaterialTheme.colors.onPrimary,
    textModifier: Modifier = Modifier.padding(
        all = LocalSpacing.current.small
    ),
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        shape = shape,
        content = {
            Text(
                modifier = textModifier,
                text = text,
                style = textStyle,
                color = textColor
            )
        }
    )
}

@Preview
@Composable
private fun ActionButtonPreview() {
    ActionButton(
        text = "Test",
        onClick = { }
    )
}