package com.intelligent.feature_tracker_domain.use_cases

import com.intelligent.feature_tracker_domain.models.TrackedFood
import com.intelligent.feature_tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodForDate(localDate = date)
    }
}