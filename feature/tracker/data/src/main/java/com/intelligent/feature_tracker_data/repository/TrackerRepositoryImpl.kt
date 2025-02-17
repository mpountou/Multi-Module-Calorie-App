package com.intelligent.feature_tracker_data.repository

import android.util.Log
import com.intelligent.feature_tracker_data.local.TrackerDao
import com.intelligent.feature_tracker_data.mappers.toTrackableFoodList
import com.intelligent.feature_tracker_data.mappers.toTrackedFoodEntity
import com.intelligent.feature_tracker_data.mappers.toTrackedFoodFlow
import com.intelligent.feature_tracker_data.remote.OpenFoodApi
import com.intelligent.feature_tracker_domain.models.TrackableFood
import com.intelligent.feature_tracker_domain.models.TrackedFood
import com.intelligent.feature_tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {

    companion object {
        const val TAG = "TrackerRepositoryImpl"
    }

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(query, page, pageSize)
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories = it.nutriments.carbohydrates100g * 4f +
                                it.nutriments.proteins100g * 4f +
                                it.nutriments.fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .toTrackableFoodList()
            )
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insert(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.delete(food.toTrackedFoodEntity())
    }

    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).toTrackedFoodFlow()
    }
}