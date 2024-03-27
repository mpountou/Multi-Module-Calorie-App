package com.intelligent.feature_onboarding_ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.intelligent.feature_api.navigation.FeatureApi

interface OnBoardingFeatureApi : FeatureApi

class OnBoardingFeatureApiImpl : OnBoardingFeatureApi {
    override fun registerGraph(
        navigationController: NavController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalOnBoardingFeatureApi.registerGraph(
            navigationController = navigationController,
            navGraphBuilder = navGraphBuilder,
        )
    }
}