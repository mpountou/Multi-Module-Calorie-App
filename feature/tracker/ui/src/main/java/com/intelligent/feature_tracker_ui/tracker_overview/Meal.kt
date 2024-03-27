package com.intelligent.feature_tracker_ui.tracker_overview

import androidx.annotation.DrawableRes
import com.intelligent.common.R
import com.intelligent.feature_tracker_domain.models.MealType

data class Meal(
    val name: Int,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)

val defaultMeals = listOf(
    Meal(
        name = R.string.breakfast,
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.Breakfast
    ),
    Meal(
        name = R.string.lunch,
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.Lunch
    ),
    Meal(
        name = R.string.dinner,
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.Dinner
    ),
    Meal(
        name = R.string.snacks,
        drawableRes = R.drawable.ic_snack,
        mealType = MealType.Snack
    ),
)
