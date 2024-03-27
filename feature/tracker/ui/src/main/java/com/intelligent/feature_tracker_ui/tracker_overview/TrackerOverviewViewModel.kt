package com.intelligent.feature_tracker_ui.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.feature_api.navigation.Route
import com.intelligent.feature_tracker_domain.models.TrackedFood
import com.intelligent.feature_tracker_domain.use_cases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) : ViewModel() {

    var state by mutableStateOf(TrackerOverviewUiState())
        private set

    private val _navigationAction = Channel<NavigationAction>()
    val navigationAction = _navigationAction.receiveAsFlow()

    init {
        preferences.saveShouldShowOnboarding(shouldShow = false)
    }

    fun onNavigationAction(navigationAction: NavigationAction) {
        when (navigationAction) {
            NavigationAction.OnNextDayClick -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                refreshFoods()
            }

            NavigationAction.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFoods()
            }

            is NavigationAction.OnAddFoodClick -> viewModelScope.launch {

                _navigationAction.send(
                    NavigationAction.NavigateToSearchScreen(
                        route = Route.SEARCH +
                                "/${navigationAction.meal.mealType.name}" +
                                "/${state.date.dayOfMonth}" +
                                "/${state.date.month}/"+
                                "/${state.date.year}/"
                    )
                )
            }

            is NavigationAction.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(trackedFood = navigationAction.trackedFood)
                    refreshFoods()
                }
            }

            is NavigationAction.OnToggleMealClick -> {
                state = state.copy(
                    meals = state.meals.map {
                        if (it.name == navigationAction.meal.name) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }

            is NavigationAction.NavigateToSearchScreen -> {}
        }
    }

    fun navigateTo(navigationAction: NavigationAction) = viewModelScope.launch {
        _navigationAction.send(navigationAction)
    }

    private fun refreshFoods() {
        trackerUseCases.getFoodsForDate(
            state.date
        ).onEach {
            val nutrientsResult = trackerUseCases.calculateMealNutrients(it)
            state = state.copy(
                totalCarbs = nutrientsResult.totalCarbs,
                totalProtein = nutrientsResult.totalProtein,
                totalFat = nutrientsResult.totalFat,
                totalCalories = nutrientsResult.totalCalories,
                carbsGoal = nutrientsResult.carbsGoal,
                proteinGoal = nutrientsResult.proteinGoal,
                fatGoal = nutrientsResult.fatGoal,
                caloriesGoal = nutrientsResult.caloriesGoal,
                trackedFoods = it,
                meals = state.meals.map {
                    val nutrientsForMeal =
                        nutrientsResult.mealNutrients[it.mealType]
                            ?: return@map it.copy(
                                carbs = 0,
                                protein = 0,
                                fat = 0,
                                calories = 0
                            )
                    it.copy(
                        carbs = nutrientsForMeal.carbs,
                        protein = nutrientsForMeal.protein,
                        fat = nutrientsForMeal.fat,
                        calories = nutrientsForMeal.calories
                    )
                }
            )
        }.launchIn(viewModelScope)
    }

    sealed class NavigationAction {
        data class NavigateToSearchScreen(val route: String) : NavigationAction()
        object OnNextDayClick : NavigationAction()
        object OnPreviousDayClick : NavigationAction()
        data class OnToggleMealClick(val meal: Meal) : NavigationAction()
        data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : NavigationAction()
        data class OnAddFoodClick(val meal: Meal) : NavigationAction()
    }

}