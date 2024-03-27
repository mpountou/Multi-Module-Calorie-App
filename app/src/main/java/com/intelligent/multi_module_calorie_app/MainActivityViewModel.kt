package com.intelligent.multi_module_calorie_app

import androidx.lifecycle.ViewModel
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.feature_api.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    fun getStartDestination(): String {
        return if (preferences.shouldShowOnboarding())
            Route.ONBOARDING_ROUTE
        else
            Route.TRACKER_ROUTE
    }

}