package com.intelligent.feature_tracker_ui.di

import com.intelligent.feature_tracker_ui.navigation.TrackerOverviewFeatureApi
import com.intelligent.feature_tracker_ui.navigation.TrackerOverviewFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object TrackerUiModule {

    @Provides
    fun provideTrackerOverviewFeatureApi(): TrackerOverviewFeatureApi =
        TrackerOverviewFeatureApiImpl()
}