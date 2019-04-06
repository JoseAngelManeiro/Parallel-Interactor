package com.joseangelmaneiro.parallelinteractor.main.presenter

import com.joseangelmaneiro.parallelinteractor.main.interactor.ParallelInteractor
import com.joseangelmaneiro.parallelinteractor.main.interactor.SequentialInteractor
import java.lang.ref.WeakReference

class MainPresenter(
  mainView: MainView,
  private val sequentialInteractor: SequentialInteractor,
  private val parallelInteractor: ParallelInteractor
) {

  companion object {
    const val HOTEL_ID = 1234
  }

  private val view = WeakReference(mainView)

  fun onSequentialButtonClicked() {
    view.get()?.startLoading()
    sequentialInteractor(
      request = SequentialInteractor.Request(HOTEL_ID),
      onSuccess = { view.get()?.finishLoading() },
      onError = {}
    )
  }

  fun onParallelButtonClicked() {
    view.get()?.startLoading()
    parallelInteractor(
      request = ParallelInteractor.Request(HOTEL_ID),
      onSuccess = { view.get()?.finishLoading() },
      onError = {}
    )
  }
}