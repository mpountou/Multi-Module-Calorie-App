package com.intelligent.feature_onboarding_ui.screens.activity_level

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.common.domain.models.ActivityLevel
import com.intelligent.common.domain.models.Gender
import com.intelligent.common.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var activityLevel: ActivityLevel by mutableStateOf(ActivityLevel.High)

    fun onActivityLevelClick(activityLevel: ActivityLevel) {
        this.activityLevel = activityLevel
    }

    private val _navigationAction = Channel<NavigationAction?>()
    val navigationAction = _navigationAction.receiveAsFlow()

    fun onButtonNextClicked() = viewModelScope.launch {
        preferences.saveActivityLevel(activityLevel)
        _navigationAction.send(NavigationAction.NavigateNextStep)
    }

    sealed class NavigationAction {
        object NavigateNextStep : NavigationAction()
    }
}