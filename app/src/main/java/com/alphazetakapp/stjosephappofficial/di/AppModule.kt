package com.alphazetakapp.stjosephappofficial.di

import android.content.Context
import com.alphazetakapp.stjosephappofficial.data.datasource.local.MeditationLocalDataSource
import com.alphazetakapp.stjosephappofficial.data.repository.MeditationRepositoryImpl
import com.alphazetakapp.stjosephappofficial.datastore.StoreEndDay
import com.alphazetakapp.stjosephappofficial.domain.repository.MeditationRepository
import com.alphazetakapp.stjosephappofficial.domain.usecase.GetLastCompletedDayUseCase
import com.alphazetakapp.stjosephappofficial.domain.usecase.GetMeditationsUseCase
import com.alphazetakapp.stjosephappofficial.domain.usecase.SetDayCompletedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStoreEndDay(@ApplicationContext context: Context): StoreEndDay {
        return StoreEndDay(context)
    }

    @Provides
    @Singleton
    fun provideMeditationLocalDataSource(): MeditationLocalDataSource {
        return MeditationLocalDataSource()
    }

    @Provides
    @Singleton
    fun provideMeditationRepository(
        localDataSource: MeditationLocalDataSource,
        storeEndDay: StoreEndDay
    ): MeditationRepository {
        return MeditationRepositoryImpl(
            localDataSource = localDataSource,
            storeEndDay = storeEndDay
        )
    }

    // Si tienes UseCases, también necesitarás sus providers
    @Provides
    @Singleton
    fun provideGetMeditationsUseCase(repository: MeditationRepository): GetMeditationsUseCase {
        return GetMeditationsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetLastCompletedDayUseCase(repository: MeditationRepository): GetLastCompletedDayUseCase {
        return GetLastCompletedDayUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSetDayCompletedUseCase(repository: MeditationRepository): SetDayCompletedUseCase {
        return SetDayCompletedUseCase(repository)
    }

//    @Provides
//    @Singleton
//    fun provideSetDayCompletedUseCase(repository: MeditationRepository): SetDayCompletedUseCase {
//        return SetDayCompletedUseCase(//repository)
//    }
}