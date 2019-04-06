package com.joseangelmaneiro.parallelinteractor.main.interactor

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.common.Interactor
import com.joseangelmaneiro.parallelinteractor.common.asyncSeq
import com.joseangelmaneiro.parallelinteractor.domain.Hotel
import com.joseangelmaneiro.parallelinteractor.domain.Review
import com.joseangelmaneiro.parallelinteractor.domain.repository.HotelRepository
import com.joseangelmaneiro.parallelinteractor.domain.repository.ReviewRepository
import javax.inject.Inject

class SequentialInteractor @Inject constructor(
  private val hotelRepository: HotelRepository,
  private val reviewRepository: ReviewRepository
): Interactor<SequentialInteractor.Request, SequentialInteractor.Response>() {

  class Request(val id: Int)

  class Response(val hotel: Hotel, val review: Review)

  override suspend fun execute(request: Request) =
    asyncSeq {
      val hotelResponse = hotelRepository.getHotel(request.id)
      val reviewResponse = reviewRepository.getReview(request.id)
      if (hotelResponse.isRight && reviewResponse.isRight) {
        Either.Right(Response(hotelResponse.rightValue, reviewResponse.rightValue))
      } else if (hotelResponse.isLeft) {
        Either.Left(hotelResponse.leftValue)
      } else {
        Either.Left(reviewResponse.leftValue)
      }
    }
}