package com.intelligent.feature_onboarding_domain.di

import com.intelligent.feature_onboarding_domain.usecase.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {

    @Provides
    fun provideValidateNutrients() = ValidateNutrients()

}