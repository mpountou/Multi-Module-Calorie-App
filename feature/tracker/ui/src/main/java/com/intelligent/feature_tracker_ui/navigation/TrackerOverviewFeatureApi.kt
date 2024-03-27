package com.intelligent.feature_tracker_ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.intelligent.feature_api.navigation.FeatureApi

interface TrackerOverviewFeatureApi : FeatureApi

class TrackerOverviewFeatureApiImpl : TrackerOverviewFeatureApi {

    override fun registerGraph(
        navigationController: NavController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalTrackerOverviewFeatureApiImpl.registerGraph(
            navigationController, navGraphBuilder
        )
    }

}