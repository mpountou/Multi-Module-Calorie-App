package com.intelligent.feature_tracker_domain.use_cases

import com.intelligent.feature_tracker_domain.models.TrackedFood
import com.intelligent.feature_tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        return repository.deleteTrackedFood(trackedFood)
    }
}