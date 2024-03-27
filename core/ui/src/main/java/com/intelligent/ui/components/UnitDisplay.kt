package com.intelligent.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.intelligent.ui.LocalSpacing


@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    amount: Int,
    unit: String,
    amountTextStyle: TextStyle = MaterialTheme.typography.h6,
    amountColor: Color = MaterialTheme.colors.onBackground,
    unitTextStyle: TextStyle = MaterialTheme.typography.body1,
    unitColor: Color = MaterialTheme.colors.onBackground
) {

    val spacing = LocalSpacing.current

    Row(
        modifier = modifier,
    ) {

        Text(
            modifier = Modifier.alignBy(LastBaseline),
            text = amount.toString(),
            style = amountTextStyle,
            color = amountColor
        )

        Spacer(modifier = Modifier.width(spacing.small))

        Text(
            modifier = Modifier.alignBy(LastBaseline),
            text = unit,
            style = unitTextStyle,
            color = unitColor
        )

    }

}

@Preview
@Composable
private fun UnitDisplayPreview() {
    UnitDisplay(amount = 2, unit = "kcal")
}

