package com.joseangelmaneiro.parallelinteractor.domain.repository

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.domain.Hotel

interface HotelRepository {
  fun getHotel(id: Int): Either<Exception, Hotel>
}