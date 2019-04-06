package com.joseangelmaneiro.parallelinteractor.common

abstract class Interactor<TReq, TRes> {

  abstract suspend fun execute(request: TReq): Either<Throwable, TRes>

  operator fun invoke(request: TReq, onError: (Throwable) -> Unit = {}, onSuccess: (TRes) -> Unit = {}) {
    launch {
      val response = execute(request)
      if (response.isRight) {
        onSuccess(response.rightValue)
      } else {
        onError(response.leftValue)
      }
    }
  }
}
