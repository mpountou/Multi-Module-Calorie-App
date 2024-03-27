package com.intelligent.feature_tracker_ui.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.intelligent.common.R
import com.intelligent.feature_tracker_ui.tracker_overview.components.ExpandableMeal
import com.intelligent.feature_tracker_ui.tracker_overview.components.TrackedFoodItem
import com.intelligent.ui.LocalSpacing
import com.intelligent.ui.components.DaySelector
import com.intelligent.feature_tracker_ui.tracker_overview.TrackerOverviewViewModel.NavigationAction.*
import com.intelligent.ui.components.AddButton

@Composable
fun TrackerOverViewScreen(
    trackerOverviewViewModel: TrackerOverviewViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val state = trackerOverviewViewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        trackerOverviewViewModel.navigationAction.collect {
            trackerOverviewViewModel.onNavigationAction(it)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.medium)
    ) {
        item {
            TrackerOverviewHeader(
                trackerOverviewUiState = state
            )
        }
        item {
            DaySelector(
                modifier = Modifier.fillMaxWidth(),
                date = state.date,
                onPreviousDateClick = {
                    trackerOverviewViewModel.navigateTo(
                        navigationAction = OnPreviousDayClick
                    )
                },
                onNextDateClick = {
                    trackerOverviewViewModel.navigateTo(
                        navigationAction = OnNextDayClick
                    )
                },
                todayStringRes = R.string.today,
                tomorrowStringRes = R.string.tomorrow,
                yesterdayStringRes = R.string.yesterday
            )
        }

        items(state.meals) {
            ExpandableMeal(
                modifier = Modifier.fillMaxWidth(),
                meal = it,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        state.trackedFoods.forEach {trackedFood ->
                            TrackedFoodItem(
                                trackedFood = trackedFood,
                                onDeleteClick = {
                                    trackerOverviewViewModel.onNavigationAction(
                                        navigationAction = OnDeleteTrackedFoodClick(
                                            trackedFood = trackedFood
                                        )
                                    )
                                }
                            )
                        }
                    }
                    AddButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.add_meal, stringResource(id = it.name)),
                        onClick = { trackerOverviewViewModel.onNavigationAction(OnAddFoodClick(it)) }
                    )
                },
                onToggleClick = { trackerOverviewViewModel.onNavigationAction(OnToggleMealClick(it)) }
            )
        }

    }

}