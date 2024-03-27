package com.intelligent.feature_onboarding_ui.screens.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.common.domain.models.GoalType
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.feature_onboarding_ui.screens.gender.GenderViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    var goal : GoalType by mutableStateOf(GoalType.LoseWeight)

    fun onGoalClick(goal: GoalType) {
        this.goal = goal
    }

    private val _navigationAction = Channel<NavigationAction?>()
    val navigationAction = _navigationAction.receiveAsFlow()

    fun onButtonNextClicked() = viewModelScope.launch {
        preferences.saveGoalType(goal)
        _navigationAction.send(NavigationAction.NavigateNextStep)
    }

    sealed class NavigationAction {
        object NavigateNextStep : NavigationAction()
    }
}