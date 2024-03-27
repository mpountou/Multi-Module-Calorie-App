package com.intelligent.feature_tracker_ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.intelligent.feature_api.navigation.FeatureApi
import com.intelligent.feature_api.navigation.Route.TRACKER_OVERVIEW
import com.intelligent.feature_api.navigation.Route.TRACKER_ROUTE
import com.intelligent.feature_tracker_ui.tracker_overview.TrackerOverViewScreen


internal object InternalTrackerOverviewFeatureApiImpl : TrackerOverviewFeatureApi {

    override fun registerGraph(
        navigationController: NavController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            route = TRACKER_ROUTE,
            startDestination = TRACKER_OVERVIEW
        ) {
            composable(
                route = TRACKER_OVERVIEW
            ) {
                TrackerOverViewScreen()
            }
        }
    }

}