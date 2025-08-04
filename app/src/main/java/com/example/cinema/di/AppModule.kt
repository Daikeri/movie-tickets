package com.example.cinema.di

import com.example.cinema.core.db.AppDatabase
import com.example.cinema.feature.movielist.data.omdb.api.OmdbApiService
import com.example.cinema.feature.movielist.data.omdb.datasource.OmdbRds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OmdbApiKey

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOmdbApiService(retrofit: Retrofit): OmdbApiService {
        return retrofit.create(OmdbApiService::class.java)
    }

    @Provides
    @OmdbApiKey
    fun provideOmdbApiKey(): String {
        return "4dc697ca"
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase) = database.movieDao()
}