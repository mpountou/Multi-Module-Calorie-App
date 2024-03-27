package com.intelligent.feature_tracker_domain.use_cases


import com.intelligent.common.domain.models.ActivityLevel
import com.intelligent.common.domain.models.Gender
import com.intelligent.common.domain.models.GoalType
import com.intelligent.common.domain.models.UserInfo
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.feature_tracker_domain.models.MealType
import com.intelligent.feature_tracker_domain.models.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )

    operator fun invoke(trackedFoods: List<TrackedFood>): Result {
        val allNutrients = trackedFoods
            .groupBy { _trackedFoods ->
                _trackedFoods.mealType
            }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                // Nutrients per meal type
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }
        // All Nutrients for all meal types
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProteins = allNutrients.values.sumOf { it.protein }
        val totalFats = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val caloryGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloryGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProteins,
            totalFat = totalFats,
            totalCalories = totalCalories,
            mealNutrients = allNutrients

        )
    }

    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female -> {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when (userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }
}