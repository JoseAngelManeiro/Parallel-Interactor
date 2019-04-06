package com.joseangelmaneiro.parallelinteractor.main.view

import android.os.Bundle
import android.view.View
import com.joseangelmaneiro.parallelinteractor.R
import com.joseangelmaneiro.parallelinteractor.main.presenter.MainPresenter
import com.joseangelmaneiro.parallelinteractor.main.presenter.MainView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainView {

  @Inject
  lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

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
