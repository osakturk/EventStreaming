package com.event.stream.controller

import com.event.stream.service.TimezoneService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(controllers = [TimezoneController::class])
class TimezoneControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var timezoneService: TimezoneService

    @Test
    fun `get timezone list`() {
        Mockito.`when`(timezoneService.retrieveAndSaveTimezones()).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/timezones")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }
}