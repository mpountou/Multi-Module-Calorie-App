package com.intelligent.feature_onboarding_domain.usecase


class ValidateNutrients {
    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): Result {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()
        if (carbsRatio == null || proteinRatio == null || fatRatio == null) {
            return Result.Error(
                errorType =  ErrorType.INVALID_VALUES
            )
        }
        if (carbsRatio + proteinRatio + fatRatio != 100) {
            return Result.Error(
                errorType = ErrorType.NOT_100_PERCENT
            )
        }
        return Result.Success(
            carbsRatio / 100f,
            proteinRatio / 100f,
            fatRatio / 100f
        )
    }

    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float
        ) : Result()

        data class Error(val errorType: ErrorType) : Result()
    }
    enum class ErrorType {
        INVALID_VALUES,
        NOT_100_PERCENT
    }
}