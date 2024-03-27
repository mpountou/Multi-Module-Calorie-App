package com.intelligent.multi_module_calorie_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.multi_module_calorie_app.navigation.NavigationProvider
import com.intelligent.multi_module_calorie_app.ui.theme.MultiModuleCalorieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiModuleCalorieAppTheme {
                val navController = rememberNavController()
                val viewModel: MainActivityViewModel = hiltViewModel()
                NavHost(
                    navController = navController,
                    startDestination = viewModel.getStartDestination()
                ) {
                    navigationProvider.onBoardingFeatureApi.registerGraph(
                        navigationController = navController,
                        navGraphBuilder = this
                    )
                    navigationProvider.trackerOverviewFeatureApi.registerGraph(
                        navigationController = navController,
                        navGraphBuilder = this
                    )
                }
            }
        }
    }

}