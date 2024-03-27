package com.intelligent.common.preferences

import android.content.SharedPreferences
import com.intelligent.common.domain.models.ActivityLevel
import com.intelligent.common.domain.models.Gender
import com.intelligent.common.domain.models.GoalType
import com.intelligent.common.domain.models.UserInfo
import com.intelligent.common.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_GENDER, gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPreferences.edit()
            .putInt(Preferences.KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPreferences.edit()
            .putInt(Preferences.KEY_HEIGHT, height)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL, level.name)
            .apply()
    }

    override fun saveGoalType(type: GoalType) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_GOAL_TYPE, type.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_CARB_RATIO, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_FAT_RATIO, ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPreferences.getInt(Preferences.KEY_AGE, 18)
        val height = sharedPreferences.getInt(Preferences.KEY_HEIGHT, 170)
        val weight = sharedPreferences.getFloat(Preferences.KEY_WEIGHT, 75F)
        val genderString = sharedPreferences.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharedPreferences.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalTypeString = sharedPreferences.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = sharedPreferences.getFloat(Preferences.KEY_CARB_RATIO, 40F)
        val proteinRatio = sharedPreferences.getFloat(Preferences.KEY_PROTEIN_RATIO, 30F)
        val fatRatio = sharedPreferences.getFloat(Preferences.KEY_FAT_RATIO, 30F)

        return UserInfo(
            gender = Gender.fromString(genderString ?: "female"),
            age = age,
            height = height,
            weight = weight,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalTypeString ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPreferences.edit().putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow).apply()
    }

    override fun shouldShowOnboarding(): Boolean {
        return sharedPreferences.getBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, true)
    }
}