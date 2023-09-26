package com.event.stream.controller

import com.event.stream.service.EventService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(controllers = [EventController::class])
class EventControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var eventService: EventService

    @Test
    fun `get event list`() {
        Mockito.`when`(eventService.events).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/events")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }

    @Test
    fun `get event list by user id`() {
        Mockito.`when`(eventService.getEventsByUserId(23)).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/events/users/23")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }

    @Test
    fun `get event list by platform`() {
        Mockito.`when`(eventService.getEventsByPlatform("netflix")).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/events/platforms/netflix")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }

    @Test
    fun `get event statics`() {
        Mockito.`when`(eventService.calculateStaticsByUserIdAndPlatform(23,"netflix")).thenReturn(Double.MIN_VALUE)

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/events/statics/23/netflix")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }
}