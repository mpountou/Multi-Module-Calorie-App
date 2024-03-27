package com.intelligent.multi_module_calorie_app.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.intelligent.common.domain.preferences.Preferences
import com.intelligent.common.preferences.DefaultPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppCommonModule {

    @Provides
    fun providesSharedPreferences(
        app: Application
    ): SharedPreferences = app.getSharedPreferences("shared_pref", MODE_PRIVATE)

    @Provides
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences = DefaultPreferences(
        sharedPreferences = sharedPreferences
    )
}