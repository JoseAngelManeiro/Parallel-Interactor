package com.joseangelmaneiro.parallelinteractor.domain.repository

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.domain.Review

interface ReviewRepository {
  fun getReview(id: Int): Either<Exception, Review>
}