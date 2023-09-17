package com.event.stream.service

import com.event.stream.TestFactory.getEvent
import com.event.stream.TestFactory.getEventList
import com.event.stream.repository.EventRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class EventServiceTest {
    private var eventRepository: EventRepository = mockk()
    private var articleService = EventService(eventRepository)


    @Test
    @Order(1)
    fun `create event`() {
        every {
            eventRepository.save(any())
        } returns getEvent()
        every {
            eventRepository.existsById(any())
        } returns false

        val response = articleService.addEvent(getEvent())

        assertEquals(getEvent().eventId, response.eventId)
        assertEquals(getEvent().showId, response.showId)
        assertEquals(getEvent().userId, response.userId)
        assertEquals(getEvent().eventDescription, response.eventDescription)
    }

    @Test
    @Order(2)
    fun `get event list`() {
        every {
            eventRepository.findAll()
        } returns getEventList()

        val response = articleService.events

        assertEquals(getEventList().get(0).eventId, response.get(0).eventId)
        assertEquals(getEventList().get(0).showId, response.get(0).showId)
        assertEquals(getEventList().get(0).userId, response.get(0).userId)
        assertEquals(getEventList().get(0).eventDescription, response.get(0).eventDescription)
    }

    @Test
    @Order(3)
    fun `get event list by user id`() {
        every {
            eventRepository.findEventByUserId(any())
        } returns getEventList()

        val response = articleService.getEventsByUserId(123)

        assertEquals(getEventList().get(0).eventId, response.get(0).eventId)
        assertEquals(getEventList().get(0).showId, response.get(0).showId)
        assertEquals(getEventList().get(0).userId, response.get(0).userId)
        assertEquals(getEventList().get(0).eventDescription, response.get(0).eventDescription)
    }
}

