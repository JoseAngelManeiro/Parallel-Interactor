package com.joseangelmaneiro.parallelinteractor.data

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.domain.Review
import com.joseangelmaneiro.parallelinteractor.domain.repository.ReviewRepository

class ReviewRepositoryImpl: ReviewRepository {

  override fun getReview(id: Int): Either<Exception, Review> {
    // Send Review after 3 seconds
    Thread.sleep(3000)
    return Either.Right(Review(count = 123, rating = 4.5))
  }
}