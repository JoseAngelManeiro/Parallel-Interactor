package com.joseangelmaneiro.parallelinteractor.main.interactor

import com.joseangelmaneiro.parallelinteractor.common.Either
import com.joseangelmaneiro.parallelinteractor.domain.Hotel
import com.joseangelmaneiro.parallelinteractor.domain.Review
import com.joseangelmaneiro.parallelinteractor.domain.repository.HotelRepository
import com.joseangelmaneiro.parallelinteractor.domain.repository.ReviewRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ParallelInteractorTest {

  private val fakeId = 1234
  private val hotel = Hotel("Hotel Name",
    "Any Address",
    "123456789",
    "hotel@parallel.com")
  private val review = Review( 111, 4.2)

  @Mock
  lateinit var hotelRepository: HotelRepository
  @Mock
  lateinit var reviewRepository: ReviewRepository

  private lateinit var parallelInteractor: ParallelInteractor

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    parallelInteractor = ParallelInteractor(hotelRepository, reviewRepository)
  }

  @Test
  fun `if all goes well we compose an response with the expected data`() {
    Mockito.`when`(hotelRepository.getHotel(fakeId)).thenReturn(Either.Right(hotel))
    Mockito.`when`(reviewRepository.getReview(fakeId)).thenReturn(Either.Right(review))

    val response = runBlocking { parallelInteractor.execute(ParallelInteractor.Request(fakeId)) }

    assertEquals(hotel, response.rightValue.hotel)
    assertEquals(review, response.rightValue.review)
  }

  @Test
  fun `if something goes wrong the hotel exception always comes first`() {
    Mockito.`when`(hotelRepository.getHotel(fakeId)).thenReturn(Either.Left(Exception("Hotel Exception")))
    Mockito.`when`(reviewRepository.getReview(fakeId)).thenReturn(Either.Left(Exception("Review Exception")))

    val response = runBlocking { parallelInteractor.execute(ParallelInteractor.Request(fakeId)) }

    assertEquals("Hotel Exception", response.leftValue.message)
  }

  @Test
  fun `the review exception is validated last`() {
    Mockito.`when`(hotelRepository.getHotel(fakeId)).thenReturn(Either.Right(hotel))
    Mockito.`when`(reviewRepository.getReview(fakeId)).thenReturn(Either.Left(Exception("Review Exception")))

    val response = runBlocking { parallelInteractor.execute(ParallelInteractor.Request(fakeId)) }

    assertEquals("Review Exception", response.leftValue.message)
  }
}