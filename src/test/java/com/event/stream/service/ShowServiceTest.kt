package com.event.stream.service

import com.event.stream.TestFactory.getShow
import com.event.stream.TestFactory.getShowList
import com.event.stream.repository.ShowRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class ShowServiceTest {
    private var showRepository: ShowRepository = mockk()
    private var showService = ShowService(showRepository)


    @Test
    @Order(1)
    fun `create show`() {
        every {
            showRepository.save(any())
        } returns getShow()
        every {
            showRepository.existsById(any())
        } returns false

        val response = showService.addShow(getShow())

        assertEquals(getShow().showId, response.showId)
        assertEquals(getShow().director, response.director)
        assertEquals(getShow().cast, response.cast)
        assertEquals(getShow().description, response.description)
        assertEquals(getShow().platform, response.platform)
    }

    @Test
    @Order(2)
    fun `get show list`() {
        val showList = getShowList()
        val showPage = PageImpl(showList)
        every {
            showRepository.findAll(PageRequest.of(0, 10))
        } returns showPage

        val response = showService.getShows(0,10)

        assertEquals(getShowList().get(0).showId, response.toList().get(0).showId)
        assertEquals(getShowList().get(0).director, response.toList().get(0).director)
        assertEquals(getShowList().get(0).cast, response.toList().get(0).cast)
        assertEquals(getShowList().get(0).description, response.toList().get(0).description)
        assertEquals(getShowList().get(0).platform, response.toList().get(0).platform)
    }

    @Test
    @Order(3)
    fun `get show casts by id`() {
        every {
            showRepository.findShowById(any())
        } returns getShow()

        val response = showService.getCastMembersById("Id")

        assertEquals(getShow().cast.split(", "), response)
    }

    @Test
    @Order(3)
    fun `get shows released after 2020`() {
        every {
            showRepository.countByReleaseYearGreaterThanEqual(any())
        } returns 12

        val response = showService.countShowsReleasedInOrAfter(2020)

        assertEquals(12, response)
    }
}

