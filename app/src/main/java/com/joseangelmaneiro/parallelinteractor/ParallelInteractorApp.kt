package com.joseangelmaneiro.parallelinteractor

import com.joseangelmaneiro.parallelinteractor.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ParallelInteractorApp : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<ParallelInteractorApp> {
    val component = DaggerAppComponent.builder().application(this).build()
    component.inject(this)
    return component
  }
}
