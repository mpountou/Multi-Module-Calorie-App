package com.intelligent.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.intelligent.ui.LocalSpacing

@Composable
fun NutrientInfo(
    modifier: Modifier = Modifier,
    amount: Int,
    unit: String,
    subTitle: String,
    amountTextStyle: TextStyle = MaterialTheme.typography.h6,
    amountColor: Color = MaterialTheme.colors.onBackground,
    subTitleStyle: TextStyle = MaterialTheme.typography.body2,
    subTitleColor: Color = MaterialTheme.colors.onBackground,
    subTitleFontWeight: FontWeight= FontWeight.Light,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            amount = amount,
            unit = unit,
            amountTextStyle = amountTextStyle,
            amountColor = amountColor
        )
        Text(
            text = subTitle,
            style = subTitleStyle,
            color = subTitleColor,
            fontWeight = subTitleFontWeight
        )
    }

}

@Preview
@Composable
private fun NutrientInfoPreview() {
    NutrientInfo(
        modifier = Modifier,
        1,
        "g",
        "Subtitle"
    )
}