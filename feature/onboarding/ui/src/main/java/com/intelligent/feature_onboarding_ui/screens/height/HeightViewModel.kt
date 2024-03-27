package com.intelligent.feature_onboarding_ui.screens.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.common.domain.models.Gender
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.common.domain.use_cases.FilterOutDigits
import com.intelligent.common.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
) : ViewModel() {

    var height by mutableStateOf("180")

    var error by mutableStateOf("")

    private val _navigationAction = Channel<NavigationAction?>()
    val navigationAction = _navigationAction.receiveAsFlow()

    fun onHeightEnter(height: String) {
        if (height.length <= 3) {
            this.height = filterOutDigits(height)
            updateError(errorMessage = null)
        }
    }

    fun onButtonNextClicked() = viewModelScope.launch {
        height.toIntOrNull().takeIf { (it ?: 0) < 250 && (it ?: 0) > 0 }?.let { height ->
            preferences.saveHeight(height)
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