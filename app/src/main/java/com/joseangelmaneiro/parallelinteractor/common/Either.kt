package com.joseangelmaneiro.parallelinteractor.common

import java.util.NoSuchElementException

sealed class Either<out L, out R> {
  data class Left<out L>(val value: L) : Either<L, Nothing>()
  data class Right<out R>(val value: R) : Either<Nothing, R>()

  val isLeft get() = this is Left
  val isRight get() = this is Right

  val leftValue: L get() = when (this) {
    is Left -> this.value
    is Right -> throw NoSuchElementException()
  }

  val rightValue: R get() = when (this) {
    is Left -> throw NoSuchElementException()
    is Right -> this.value
  }
}
