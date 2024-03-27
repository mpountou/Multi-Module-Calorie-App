package com.intelligent.feature_onboarding_ui.screens.weight

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.intelligent.common.R
import com.intelligent.ui.LocalSpacing
import com.intelligent.feature_onboarding_ui.screens.weight.WeightViewModel.NavigationAction.*
import com.intelligent.ui.components.ActionButton
import com.intelligent.ui.components.UnitTextField

@Composable
fun WeightScreen(
    viewModel: WeightViewModel = hiltViewModel(),
    onButtonNextClick: () -> Unit = {},
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationAction.collect { navigationAction ->
            when (navigationAction) {
                NavigateNextStep -> onButtonNextClick()
                is ShowError -> viewModel.error =
                    navigationAction.errorMessage
                        ?.let { context.getString(it) }
                        .orEmpty()

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
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(spacing.medium))
            UnitTextField(
                value = viewModel.weight,
                onValueChange = viewModel::onWeightEnter,
                unit = stringResource(id = R.string.kg)
            )
            Text(
                text = viewModel.error,
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