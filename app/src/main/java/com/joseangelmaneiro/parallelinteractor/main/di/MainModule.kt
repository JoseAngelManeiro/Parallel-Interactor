package com.joseangelmaneiro.parallelinteractor.main.di

import com.joseangelmaneiro.parallelinteractor.di.PerActivity
import com.joseangelmaneiro.parallelinteractor.main.interactor.ParallelInteractor
import com.joseangelmaneiro.parallelinteractor.main.interactor.SequentialInteractor
import com.joseangelmaneiro.parallelinteractor.main.presenter.MainPresenter
import com.joseangelmaneiro.parallelinteractor.main.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule {

  @Provides
  @PerActivity
  fun providePresenter(
    mainActivity: MainActivity,
    sequentialInteractor: SequentialInteractor,
    parallelInteractor: ParallelInteractor
  ): MainPresenter {
    return MainPresenter(
      mainActivity,
      sequentialInteractor,
      parallelInteractor
    )
  }
}
