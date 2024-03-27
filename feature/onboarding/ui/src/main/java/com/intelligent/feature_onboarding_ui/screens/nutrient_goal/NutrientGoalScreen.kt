package com.intelligent.feature_onboarding_ui.screens.nutrient_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.intelligent.common.R
import com.intelligent.feature_onboarding_ui.screens.nutrient_goal.NutrientGoalViewModel.NavigationAction.NavigateNextStep
import com.intelligent.ui.LocalSpacing
import com.intelligent.ui.components.ActionButton
import com.intelligent.ui.components.UnitTextField

@Composable
fun NutrientGoalScreen(
    viewModel: NutrientGoalViewModel = hiltViewModel(),
    onButtonNextClick: () -> Unit
) {

    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationAction.collect {
            when (it) {
                NavigateNextStep -> onButtonNextClick()
                null -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.medium)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(spacing.medium))
            UnitTextField(
                value = viewModel.uiState.carbsRatio,
                onValueChange = viewModel::onCardRatioEnter,
                unit = stringResource(id = R.string.carbs)
            )

            Spacer(modifier = Modifier.height(spacing.medium))
            UnitTextField(
                value = viewModel.uiState.fatRatio,
                onValueChange = viewModel::onFatRatioEnter,
                unit = stringResource(id = R.string.fat)
            )

            Spacer(modifier = Modifier.height(spacing.medium))
            UnitTextField(
                value = viewModel.uiState.proteinRatio,
                onValueChange = viewModel::onProteinRatioEnter,
                unit = stringResource(id = R.string.protein)
            )
            Text(
                text = viewModel.uiState.error,
                style = MaterialTheme.typography.body1,
                color = Color.Red
            )
        }
        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = { viewModel.onButtonNextClicked() }
        )
    }

}