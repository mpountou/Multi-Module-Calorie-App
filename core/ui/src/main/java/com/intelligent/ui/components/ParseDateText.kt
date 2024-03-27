package com.intelligent.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.ui.text.TextStyle

@Composable
fun ParseDateText(
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.h6,
    date: LocalDate,
    @StringRes todayStringRes: Int,
    @StringRes tomorrowStringRes: Int,
    @StringRes yesterdayStringRes: Int
) {

    val today = LocalDate.now()
    val tomorrow = LocalDate.now().plusDays(1)
    val yesterday = LocalDate.now().minusDays(1)

    val dateText = when (date) {
        yesterday -> stringResource(id = yesterdayStringRes)
        today -> stringResource(id = todayStringRes)
        tomorrow -> stringResource(id = tomorrowStringRes)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }

    Text(
        modifier = modifier,
        text = dateText,
        style = style
    )
}