package com.intelligent.feature_onboarding_ui.di

import com.intelligent.feature_onboarding_ui.navigation.OnBoardingFeatureApi
import com.intelligent.feature_onboarding_ui.navigation.OnBoardingFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object OnBoardingUiLayerModule {

    @Provides
    fun providesOnBoardingFeatureApi(): OnBoardingFeatureApi = OnBoardingFeatureApiImpl()

}