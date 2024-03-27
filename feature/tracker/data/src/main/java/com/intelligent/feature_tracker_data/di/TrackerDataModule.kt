package com.intelligent.feature_tracker_data.di

import android.app.Application
import androidx.room.Room
import com.intelligent.feature_tracker_data.local.TrackerDatabase
import com.intelligent.feature_tracker_data.remote.OpenFoodApi
import com.intelligent.feature_tracker_data.remote.OpenFoodApi.Companion.BASE_URL
import com.intelligent.feature_tracker_data.repository.TrackerRepositoryImpl
import com.intelligent.feature_tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): OpenFoodApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
            .create(OpenFoodApi::class.java)

    @Provides
    @Singleton
    fun provideTrackerDatabase(
        app: Application
    ): TrackerDatabase {
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            TrackerDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: OpenFoodApi,
        database: TrackerDatabase
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            dao = database.dao,
            api = api
        )
    }

}