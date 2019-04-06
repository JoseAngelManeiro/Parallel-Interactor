package com.joseangelmaneiro.parallelinteractor.common

import kotlinx.coroutines.*

fun <T> async(function: () -> T): Deferred<T> {
  return CoroutineScope(Dispatchers.IO).async { function() }
}

fun launch(block: suspend CoroutineScope.() -> Unit): Job {
  return CoroutineScope(Dispatchers.Main).launch { block() }
}

suspend fun <T> asyncSeq(block: suspend CoroutineScope.() -> T): T {
  return withContext(Dispatchers.IO) { block() }
}