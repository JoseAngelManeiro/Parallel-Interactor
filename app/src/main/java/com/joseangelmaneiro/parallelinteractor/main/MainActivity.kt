package com.joseangelmaneiro.parallelinteractor.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseangelmaneiro.parallelinteractor.R
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
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
