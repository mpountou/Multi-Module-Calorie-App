package com.intelligent.feature_onboarding_ui.screens.nutrient_goal

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.feature_onboarding_domain.usecase.ValidateNutrients
import com.intelligent.common.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.intelligent.feature_onboarding_domain.usecase.ValidateNutrients.ErrorType
import com.intelligent.feature_onboarding_domain.usecase.ValidateNutrients.Result.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.ref.WeakReference

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val validateNutrients: ValidateNutrients,
    private val preferences: Preferences
) : ViewModel() {

    private val context = WeakReference(context)

    private val _navigationAction = Channel<NavigationAction?>()
    val navigationAction = _navigationAction.receiveAsFlow()

    var uiState by mutableStateOf(NutrientGoalUiState())

    fun onCardRatioEnter(carbsRatio: String) {
        uiState = uiState.copy(carbsRatio = carbsRatio)
        updateError(message = null)
    }

    fun onProteinRatioEnter(proteinRatio: String) {
        uiState = uiState.copy(proteinRatio = proteinRatio)
        updateError(message = null)
    }

    fun onFatRatioEnter(fatRatio: String) {
        uiState = uiState.copy(fatRatio = fatRatio)
        updateError(message = null)
    }

    fun onButtonNextClicked() = viewModelScope.launch {

        when (
            val data = validateNutrients(
                carbsRatioText = uiState.carbsRatio,
                proteinRatioText = uiState.proteinRatio,
                fatRatioText = uiState.fatRatio,
            )) {
            is Error -> {
                when (data.errorType) {
                    ErrorType.INVALID_VALUES -> updateError(R.string.error_invalid_values)
                    ErrorType.NOT_100_PERCENT -> updateError(R.string.error_not_100_percent)
                }
            }

            is Success -> navigateNextStep(data)
        }


    }


    private fun navigateNextStep(data: ValidateNutrients.Result.Success) = viewModelScope.launch {
        preferences.saveCarbRatio(data.carbsRatio)
        preferences.saveFatRatio(data.fatRatio)
        preferences.saveProteinRatio(data.proteinRatio)
        _navigationAction.send(NavigationAction.NavigateNextStep)
    }

    private fun updateError(message: Int?) = viewModelScope.launch {
        val error = message?.let { context.get()?.getString(message).orEmpty() }.orEmpty()
        uiState = uiState.copy(error = error)
    }

    sealed class NavigationAction {
        object NavigateNextStep : NavigationAction()
    }
}