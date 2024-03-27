package com.intelligent.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.LocalDate

@Composable
fun DaySelector(
    date: LocalDate,
    onPreviousDateClick: () -> Unit,
    onNextDateClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes todayStringRes: Int,
    @StringRes tomorrowStringRes: Int,
    @StringRes yesterdayStringRes: Int
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPreviousDateClick,
            content = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        )

        ParseDateText(
            date = date,
            todayStringRes = todayStringRes,
            tomorrowStringRes = tomorrowStringRes,
            yesterdayStringRes = yesterdayStringRes
        )

        IconButton(
            onClick = onNextDateClick,
            content = {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
        )

    }

}