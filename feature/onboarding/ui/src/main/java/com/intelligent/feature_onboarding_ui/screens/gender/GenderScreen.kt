package com.intelligent.feature_onboarding_ui.screens.gender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.intelligent.common.R
import com.intelligent.common.domain.models.Gender
import com.intelligent.ui.LocalSpacing
import com.intelligent.feature_onboarding_ui.screens.gender.GenderViewModel.NavigationAction.*
import com.intelligent.ui.components.ActionButton
import com.intelligent.ui.components.SelectableButton

@Composable
fun GenderScreen(
    viewModel: GenderViewModel = hiltViewModel(),
    onButtonNextClick: () -> Unit = {},
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
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(spacing.medium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.male),
                    isSelected = viewModel.selectedGender is Gender.Male,
                    onClick = { viewModel.onGenderClick(Gender.Male) }
                )
                Spacer(modifier = Modifier.width(spacing.small))
                SelectableButton(
                    text = stringResource(id = R.string.female),
                    isSelected = viewModel.selectedGender is Gender.Female,
                    onClick = { viewModel.onGenderClick(Gender.Female) }
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