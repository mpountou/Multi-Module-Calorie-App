package com.intelligent.multi_module_calorie_app.navigation

import com.intelligent.feature_onboarding_ui.navigation.OnBoardingFeatureApi
import com.intelligent.feature_tracker_ui.navigation.TrackerOverviewFeatureApi

data class NavigationProvider(
    val onBoardingFeatureApi: OnBoardingFeatureApi,
    val trackerOverviewFeatureApi: TrackerOverviewFeatureApi
)
