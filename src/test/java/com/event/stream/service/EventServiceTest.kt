package com.event.stream.service

import com.event.stream.TestFactory.getEvent
import com.event.stream.TestFactory.getEventList
import com.event.stream.repository.EventRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class EventServiceTest {
    private var eventRepository: EventRepository = mockk()
    private var eventService = EventService(eventRepository)


    @Test
    @Order(1)
    fun `create event`() {
        every {
            eventRepository.save(any())
        } returns getEvent()
        every {
            eventRepository.existsById(any())
        } returns false

        val response = eventService.addEvent(getEvent())

        assertEquals(getEvent().eventId, response.eventId)
        assertEquals(getEvent().showId, response.showId)
        assertEquals(getEvent().userId, response.userId)
        assertEquals(getEvent().eventDescription, response.eventDescription)
    }

    @Test
    @Order(2)
    fun `get event list`() {
        val eventList = getEventList()
        val eventPage = PageImpl(eventList)

        every {
            eventRepository.findAll(PageRequest.of(0, 10))
        } returns eventPage

        val response = eventService.getEvents(0,10)

        assertEquals(eventList[0].eventId, response.toList()[0].eventId)
        assertEquals(eventList[0].showId, response.toList()[0].showId)
        assertEquals(eventList[0].userId, response.toList()[0].userId)
        assertEquals(eventList[0].eventDescription, response.toList()[0].eventDescription)
    }

    @Test
    @Order(3)
    fun `get event list by user id`() {
        every {
            eventRepository.findEventByUserId(any())
        } returns getEventList()

        val response = eventService.getEventsByUserId(123)

        assertEquals(getEventList().get(0).eventId, response.get(0).eventId)
        assertEquals(getEventList().get(0).showId, response.get(0).showId)
        assertEquals(getEventList().get(0).userId, response.get(0).userId)
        assertEquals(getEventList().get(0).eventDescription, response.get(0).eventDescription)
    }

    @Test
    fun `get event list by platform`() {
        every {
            eventRepository.findEventByPlatform(any())
        } returns getEventList()

        val response = eventService.getEventsByPlatform("Sytflix")

        assertEquals(getEventList().get(0).eventId, response.get(0).eventId)
        assertEquals(getEventList().get(0).showId, response.get(0).showId)
        assertEquals(getEventList().get(0).userId, response.get(0).userId)
        assertEquals(getEventList().get(0).eventDescription, response.get(0).eventDescription)
    }


    @Test
    @Order(4)
    fun `calculate statics`() {
        every {
            eventRepository.findEventByUserIdAndPlatform(any(), any())
        } returns getEventList()

        val response = eventService.calculateStaticsByUserIdAndPlatform(123, "netflix")

        assertEquals(0.0, response)
    }
}

