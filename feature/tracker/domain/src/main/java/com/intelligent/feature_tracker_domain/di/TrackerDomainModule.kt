package com.intelligent.feature_tracker_domain.di

import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.feature_tracker_domain.repository.TrackerRepository
import com.intelligent.feature_tracker_domain.use_cases.CalculateMealNutrients
import com.intelligent.feature_tracker_domain.use_cases.DeleteTrackedFood
import com.intelligent.feature_tracker_domain.use_cases.GetFoodsForDate
import com.intelligent.feature_tracker_domain.use_cases.SearchFood
import com.intelligent.feature_tracker_domain.use_cases.TrackFood
import com.intelligent.feature_tracker_domain.use_cases.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            calculateMealNutrients = CalculateMealNutrients(preferences),
            deleteTrackedFood = DeleteTrackedFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            searchFood = SearchFood(repository),
            trackFood = TrackFood(repository)
        )
    }

}