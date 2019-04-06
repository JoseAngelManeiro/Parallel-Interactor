package com.joseangelmaneiro.parallelinteractor.di

import android.app.Application
import com.joseangelmaneiro.parallelinteractor.ParallelInteractorApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<ParallelInteractorApp> {

  override fun inject(app: ParallelInteractorApp)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder
    fun build(): AppComponent
  }
}
