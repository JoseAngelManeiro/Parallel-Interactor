package com.joseangelmaneiro.parallelinteractor.domain.repository

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.domain.Review

interface ReviewRepository {
  fun getReview(): Either<Exception, Review>
}