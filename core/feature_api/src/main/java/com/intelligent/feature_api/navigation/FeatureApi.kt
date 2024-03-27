package com.intelligent.feature_api.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface FeatureApi {

    fun registerGraph(
        navigationController: NavController,
        navGraphBuilder: NavGraphBuilder
    )

}