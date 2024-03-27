package com.intelligent.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.intelligent.ui.CarbColor
import com.intelligent.ui.FatColor
import com.intelligent.ui.ProteinColor

@Composable
fun NutrientsBar(
    modifier: Modifier = Modifier,
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int
) {

    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error

    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWidthRatio.animateTo(
            targetValue = ((protein * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / calorieGoal)
        )
    }

    Canvas(modifier = modifier) {

        when {
            calories <= calorieGoal -> {
                val carbsWidth = carbWidthRatio.value * size.width
                val proteinWidth = proteinWidthRatio.value * size.width
                val fatWidth = fatWidthRatio.value * size.width
                drawRoundRect(
                    color = background,
                    size = size,
                    cornerRadius = CornerRadius(100f)
                )
                drawRoundRect(
                    color = FatColor,
                    size = Size(
                        width = fatWidth + proteinWidth + carbsWidth,
                        height = size.height
                    ),
                    cornerRadius = CornerRadius(100f)
                )
                drawRoundRect(
                    color = ProteinColor,
                    size = Size(
                        width = proteinWidth + carbsWidth,
                        height = size.height
                    ),
                    cornerRadius = CornerRadius(100f)
                )

                drawRoundRect(
                    color = CarbColor,
                    size = Size(
                        width = carbsWidth,
                        height = size.height
                    ),
                    cornerRadius = CornerRadius(100f)
                )
            }
            else -> {
                drawRoundRect(
                    color = caloriesExceedColor,
                    size = size,
                    cornerRadius = CornerRadius(100f)
                )
            }
        }

    }
}