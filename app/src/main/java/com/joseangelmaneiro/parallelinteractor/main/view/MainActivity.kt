package com.joseangelmaneiro.parallelinteractor.main.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseangelmaneiro.parallelinteractor.R
import com.joseangelmaneiro.parallelinteractor.data.HotelRepositoryImpl
import com.joseangelmaneiro.parallelinteractor.data.ReviewRepositoryImpl
import com.joseangelmaneiro.parallelinteractor.main.interactor.ParallelInteractor
import com.joseangelmaneiro.parallelinteractor.main.interactor.SequentialInteractor
import com.joseangelmaneiro.parallelinteractor.main.presenter.MainPresenter
import com.joseangelmaneiro.parallelinteractor.main.presenter.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

  lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    presenter = MainPresenter(
      mainView = this,
      sequentialInteractor = SequentialInteractor(HotelRepositoryImpl(), ReviewRepositoryImpl()),
      parallelInteractor = ParallelInteractor(HotelRepositoryImpl(), ReviewRepositoryImpl())
    )

    initViews()
  }

  private fun initViews() {
    sequentialButton.setOnClickListener {
      presenter.onSequentialButtonClicked()
    }
    parallelButton.setOnClickListener {
      presenter.onParallelButtonClicked()
    }
  }

  override fun startLoading() {
    progressBar.visibility = View.VISIBLE
    startValueTextView.text = getCurrentTime()
    finishValueTextView.text = ""
  }

  override fun finishLoading() {
    progressBar.visibility = View.INVISIBLE
    finishValueTextView.text = getCurrentTime()
  }

  private fun getCurrentTime(): String {
    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
    return dateFormat.format(Calendar.getInstance().time)
  }
}
