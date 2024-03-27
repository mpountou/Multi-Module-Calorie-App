package com.intelligent.feature_onboarding_ui.screens.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.common.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var weight by mutableStateOf("80.0")

    var error by mutableStateOf("")

    private val _navigationAction = Channel<NavigationAction?>()
    val navigationAction = _navigationAction.receiveAsFlow()

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) {
            this.weight = weight
            updateError(errorMessage = null)
        }
    }

    fun onButtonNextClicked() = viewModelScope.launch {
        weight.toFloatOrNull().takeIf { (it ?: 0f) < 350f && (it ?: 0f) > 0f }?.let { weight ->
            preferences.saveWeight(weight)
            _navigationAction.send(NavigationAction.NavigateNextStep)
        } ?: kotlin.run {
            updateError(errorMessage = R.string.enter_a_valid_height)
        }
    }

    private fun updateError(errorMessage: Int?) = viewModelScope.launch {
        NavigationAction.ShowError(
            errorMessage = errorMessage
        )
    }

    sealed class NavigationAction {
        object NavigateNextStep : NavigationAction()
        data class ShowError(val errorMessage: Int?) : NavigationAction()
    }
}