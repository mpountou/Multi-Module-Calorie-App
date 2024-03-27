package com.intelligent.feature_onboarding_ui.screens.activity_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.intelligent.common.R
import com.intelligent.common.domain.models.ActivityLevel
import com.intelligent.ui.LocalSpacing
import com.intelligent.feature_onboarding_ui.screens.activity_level.ActivityLevelViewModel.NavigationAction.*
import com.intelligent.ui.components.ActionButton
import com.intelligent.ui.components.SelectableButton

@Composable
fun ActivityLevelScreen(
    viewModel: ActivityLevelViewModel = hiltViewModel(),
    onButtonNextClick: () -> Unit = {}
) {


    val spacing = LocalSpacing.current
    val context = LocalContext.current

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
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h6
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = viewModel.activityLevel is ActivityLevel.High,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.High) }
                )

                Spacer(modifier = Modifier.width(spacing.small))

                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = viewModel.activityLevel is ActivityLevel.Medium,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.Medium) }
                )

                Spacer(modifier = Modifier.width(spacing.small))

                SelectableButton(
                    text = stringResource(id = R.string.low),
                    isSelected = viewModel.activityLevel is ActivityLevel.Low,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.Low) }
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