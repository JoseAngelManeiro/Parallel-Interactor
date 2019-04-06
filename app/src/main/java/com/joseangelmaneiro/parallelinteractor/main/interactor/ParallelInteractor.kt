package com.joseangelmaneiro.parallelinteractor.main.interactor

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.common.Interactor
import com.joseangelmaneiro.parallelinteractor.common.async
import com.joseangelmaneiro.parallelinteractor.domain.Hotel
import com.joseangelmaneiro.parallelinteractor.domain.Review
import com.joseangelmaneiro.parallelinteractor.domain.repository.HotelRepository
import com.joseangelmaneiro.parallelinteractor.domain.repository.ReviewRepository

class ParallelInteractor constructor(
  private val hotelRepository: HotelRepository,
  private val reviewRepository: ReviewRepository
): Interactor<ParallelInteractor.Request, ParallelInteractor.Response>() {

  class Request(val id: Int)

  class Response(val hotel: Hotel, val review: Review)

  override suspend fun execute(request: Request): Either<Throwable, Response> {
    val hotelFuture = async { hotelRepository.getHotel(request.id) }
    val reviewFuture = async { reviewRepository.getReview(request.id) }

    val hotelResponse = hotelFuture.await()
    val reviewResponse = reviewFuture.await()

    return if (hotelResponse.isRight && reviewResponse.isRight) {
      Either.Right(Response(hotelResponse.rightValue, reviewResponse.rightValue))
    } else if (hotelResponse.isLeft) {
      Either.Left(hotelResponse.leftValue)
    } else {
      Either.Left(reviewResponse.leftValue)
    }
  }
}