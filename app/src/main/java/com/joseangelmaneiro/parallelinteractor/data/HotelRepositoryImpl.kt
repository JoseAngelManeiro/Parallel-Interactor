package com.joseangelmaneiro.parallelinteractor.data

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.domain.Hotel
import com.joseangelmaneiro.parallelinteractor.domain.repository.HotelRepository

class HotelRepositoryImpl: HotelRepository {

  override fun getHotel(id: Int): Either<Exception, Hotel> {
    // Send Hotel after 3 seconds
    Thread.sleep(3000)
    return Either.Right(
      Hotel(
        name = "Hotel Parallel",
        address = "Somewhere in the domain",
        phone = "123456789",
        email = "hotel@parallel.com"
      )
    )
  }
}