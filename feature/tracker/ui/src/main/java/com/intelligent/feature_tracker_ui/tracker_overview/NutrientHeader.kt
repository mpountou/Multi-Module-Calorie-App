package com.intelligent.feature_tracker_ui.tracker_overview

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.intelligent.common.R
import com.intelligent.feature_tracker_domain.models.MealType
import com.intelligent.ui.CarbColor
import com.intelligent.ui.FatColor
import com.intelligent.ui.LocalSpacing
import com.intelligent.ui.ProteinColor
import com.intelligent.ui.components.NutrientBarInfo
import com.intelligent.ui.components.NutrientsBar
import com.intelligent.ui.components.UnitDisplay
import org.intellij.lang.annotations.JdkConstants
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun TrackerOverviewHeader(
    modifier: Modifier = Modifier,
    trackerOverviewUiState: TrackerOverviewUiState
) {

    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = trackerOverviewUiState.totalCalories
    )
    val state = trackerOverviewUiState
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = spacing.large,
                vertical = spacing.large
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Bottom
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(id = R.string.kcal),
                amountColor = MaterialTheme.colors.onPrimary,
                unitColor = MaterialTheme.colors.onPrimary
            )
            Column {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
                UnitDisplay(
                    amount = animatedCalorieCount.value,
                    unit = stringResource(id = R.string.kcal),
                    amountColor = MaterialTheme.colors.onPrimary,
                    unitColor = MaterialTheme.colors.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.medium))


        NutrientsBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal,
        )

        Spacer(modifier = Modifier.height(spacing.large))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(
                    id = R.string.carbs
                ),
                color = CarbColor,
                modifier = Modifier.size(90.dp),
                unitName = R.string.grams
            )
            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(
                    id = R.string.protein
                ),
                color = ProteinColor,
                modifier = Modifier.size(90.dp),
                unitName = R.string.grams
            )
            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(
                    id = R.string.fat
                ),
                color = FatColor,
                modifier = Modifier.size(90.dp),
                unitName = R.string.grams
            )
        }

    }
}