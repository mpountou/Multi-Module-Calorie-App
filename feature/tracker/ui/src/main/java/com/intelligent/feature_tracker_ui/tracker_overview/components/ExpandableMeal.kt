package com.intelligent.feature_tracker_ui.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.intelligent.common.R
import com.intelligent.feature_tracker_ui.tracker_overview.Meal
import com.intelligent.feature_tracker_ui.tracker_overview.defaultMeals
import com.intelligent.ui.LocalSpacing
import com.intelligent.ui.components.NutrientInfo
import com.intelligent.ui.components.UnitDisplay

@Composable
fun ExpandableMeal(
    meal: Meal,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onToggleClick: () -> Unit
) {

    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleClick() }
                .padding(spacing.medium)
        ) {
            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = stringResource(id = meal.name)
            )
            Spacer(modifier = Modifier.width(spacing.small))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = meal.name),
                        style = MaterialTheme.typography.h5
                    )
                    Icon(
                        imageVector = getArrowState(meal),
                        contentDescription = null
                    )
                }
                Row {
                    UnitDisplay(
                        amount = meal.calories,
                        unit = stringResource(id = R.string.kcal),
                        amountTextStyle = MaterialTheme.typography.h5,
                        unitTextStyle = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = Modifier.width(spacing.medium))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        NutrientInfo(
                            amount = meal.fat,
                            unit = stringResource(id = R.string.grams),
                            subTitle = stringResource(id = R.string.fat),
                        )
                        NutrientInfo(
                            amount = meal.fat,
                            unit = stringResource(id = R.string.grams),
                            subTitle = stringResource(id = R.string.fat),
                        )
                        NutrientInfo(
                            amount = meal.fat,
                            unit = stringResource(id = R.string.grams),
                            subTitle = stringResource(id = R.string.fat),
                        )
                    }
                }
            }
        }
        AnimatedVisibility(visible = meal.isExpanded)  {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                content()
            }
        }

    }
}

private fun getArrowState(meal: Meal) =
    if (meal.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

@Preview
@Composable
fun ExpandableMealPreview() {
    ExpandableMeal(meal = defaultMeals[0], content = { /*TODO*/ }) {

    }
}