package com.intelligent.multi_module_calorie_app.di

import com.intelligent.common.domain.use_cases.FilterOutDigits
import com.intelligent.feature_onboarding_ui.navigation.OnBoardingFeatureApi
import com.intelligent.feature_tracker_ui.navigation.TrackerOverviewFeatureApi
import com.intelligent.multi_module_calorie_app.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppUiModule {

    @Provides
    fun provideNavigationProvider(
        onBoardingFeatureApi: OnBoardingFeatureApi,
        trackerOverviewFeatureApi: TrackerOverviewFeatureApi
    ) =
        NavigationProvider(
            onBoardingFeatureApi = onBoardingFeatureApi,
            trackerOverviewFeatureApi = trackerOverviewFeatureApi
        )

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase(): FilterOutDigits {
        return FilterOutDigits()
    }
}