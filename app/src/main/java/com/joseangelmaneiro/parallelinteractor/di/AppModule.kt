package com.joseangelmaneiro.parallelinteractor.di

import com.joseangelmaneiro.parallelinteractor.data.HotelRepositoryImpl
import com.joseangelmaneiro.parallelinteractor.data.ReviewRepositoryImpl
import com.joseangelmaneiro.parallelinteractor.domain.repository.HotelRepository
import com.joseangelmaneiro.parallelinteractor.domain.repository.ReviewRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun provideHotelRepository(): HotelRepository {
    return HotelRepositoryImpl()
  }

  @Provides
  @Singleton
  fun provideReviewRepository(): ReviewRepository {
    return ReviewRepositoryImpl()
  }
}
