package com.joseangelmaneiro.parallelinteractor.di

import com.joseangelmaneiro.parallelinteractor.main.di.MainModule
import com.joseangelmaneiro.parallelinteractor.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = [(MainModule::class)])
  @PerActivity
  internal abstract fun bindMainActivity(): MainActivity
}
