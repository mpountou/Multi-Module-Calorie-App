package com.intelligent.feature_onboarding_ui.screens.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.intelligent.common.domain.models.GoalType
import com.intelligent.ui.LocalSpacing
import com.intelligent.feature_onboarding_ui.screens.goal.GoalViewModel.NavigationAction.*
import com.intelligent.ui.components.ActionButton
import com.intelligent.ui.components.SelectableButton
import com.intelligent.common.R

@Composable
fun GoalScreen(
    viewModel: GoalViewModel = hiltViewModel(),
    onButtonNextClick: () -> Unit
) {

    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationAction.collect { navigationAction ->
            when (navigationAction) {
                NavigateNextStep -> onButtonNextClick()
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.medium)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.h6
            )

            Row {
                SelectableButton(text = stringResource(id = R.string.lose),
                    isSelected =
                    viewModel.goal is GoalType.LoseWeight,
                    onClick = { viewModel.onGoalClick(GoalType.LoseWeight) }
                )
                Spacer(modifier = Modifier.width(spacing.small))
                SelectableButton(text = stringResource(id = R.string.gain),
                    isSelected =
                    viewModel.goal is GoalType.GainWeight,
                    onClick = { viewModel.onGoalClick(GoalType.GainWeight) }
                )
                Spacer(modifier = Modifier.width(spacing.small))
                SelectableButton(text = stringResource(id = R.string.keep),
                    isSelected =
                    viewModel.goal is GoalType.KeepWeight,
                    onClick = { viewModel.onGoalClick(GoalType.KeepWeight) }
                )
            }
        }
        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onClick = { viewModel.onButtonNextClicked() }
        )
    }
}