package com.joseangelmaneiro.parallelinteractor.data

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.domain.Review
import com.joseangelmaneiro.parallelinteractor.domain.repository.ReviewRepository

class ReviewRepositoryImpl: ReviewRepository {

  override fun getReview(): Either<Exception, Review> {
    // Send Review after 2 seconds
    Thread.sleep(2000)
    return Either.Right(Review(count = 123, rating = 4.5))
  }
}