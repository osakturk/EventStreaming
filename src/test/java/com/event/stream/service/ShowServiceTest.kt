package com.event.stream.service

import com.event.stream.TestFactory
import com.event.stream.TestFactory.getShow
import com.event.stream.TestFactory.getShowList
import com.event.stream.repository.ShowRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import java.util.*

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
        every {
            showRepository.findAll()
        } returns getShowList()

        val response = showService.shows

        assertEquals(getShowList().get(0).showId, response.get(0).showId)
        assertEquals(getShowList().get(0).director, response.get(0).director)
        assertEquals(getShowList().get(0).cast, response.get(0).cast)
        assertEquals(getShowList().get(0).description, response.get(0).description)
        assertEquals(getShowList().get(0).platform, response.get(0).platform)
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
}

