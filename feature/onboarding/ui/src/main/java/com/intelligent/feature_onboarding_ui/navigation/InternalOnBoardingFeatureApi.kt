package com.intelligent.feature_onboarding_ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.intelligent.feature_api.navigation.FeatureApi
import com.intelligent.feature_api.navigation.Route.ACTIVITY
import com.intelligent.feature_api.navigation.Route.AGE
import com.intelligent.feature_api.navigation.Route.GENDER
import com.intelligent.feature_api.navigation.Route.GOAL
import com.intelligent.feature_api.navigation.Route.HEIGHT
import com.intelligent.feature_api.navigation.Route.NUTRIENT_GOAL
import com.intelligent.feature_api.navigation.Route.ONBOARDING_ROUTE
import com.intelligent.feature_api.navigation.Route.TRACKER_ROUTE
import com.intelligent.feature_api.navigation.Route.WEIGHT
import com.intelligent.feature_api.navigation.Route.WELCOME
import com.intelligent.feature_onboarding_ui.screens.activity_level.ActivityLevelScreen
import com.intelligent.feature_onboarding_ui.screens.age.AgeScreen
import com.intelligent.feature_onboarding_ui.screens.gender.GenderScreen
import com.intelligent.feature_onboarding_ui.screens.goal.GoalScreen
import com.intelligent.feature_onboarding_ui.screens.height.HeightScreen
import com.intelligent.feature_onboarding_ui.screens.nutrient_goal.NutrientGoalScreen
import com.intelligent.feature_onboarding_ui.screens.weight.WeightScreen
import com.intelligent.feature_onboarding_ui.screens.welcome.screen_welcome.WelcomeScreen

internal object InternalOnBoardingFeatureApi : FeatureApi {
    override fun registerGraph(
        navigationController: NavController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            route = ONBOARDING_ROUTE,
            startDestination = WELCOME
        ) {
            composable(route = WELCOME) {
                WelcomeScreen(
                    onButtonNextClick = { navigationController.navigate(GENDER) }
                )
            }
            composable(route = GENDER) {
                GenderScreen(
                    onButtonNextClick = { navigationController.navigate(AGE) }
                )
            }
            composable(route = AGE) {
                AgeScreen(
                    onButtonNextClick = { navigationController.navigate(HEIGHT) }
                )
            }
            composable(route = HEIGHT) {
                HeightScreen(
                    onButtonNextClick = { navigationController.navigate(WEIGHT) }
                )
            }
            composable(route = WEIGHT) {
                WeightScreen(
                    onButtonNextClick = { navigationController.navigate(ACTIVITY) }
                )
            }
            composable(route = ACTIVITY) {
                ActivityLevelScreen(
                    onButtonNextClick = { navigationController.navigate(GOAL) }
                )
            }
            composable(route = GOAL) {
                GoalScreen(
                    onButtonNextClick = { navigationController.navigate(NUTRIENT_GOAL) }
                )
            }
            composable(route = NUTRIENT_GOAL) {
                NutrientGoalScreen(
                    onButtonNextClick = { navigationController.navigate(TRACKER_ROUTE) }
                )
            }


        }
    }
}