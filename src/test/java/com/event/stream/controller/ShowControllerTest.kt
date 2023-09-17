package com.event.stream.controller

import com.event.stream.service.ShowService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@WebMvcTest(controllers = [ShowController::class])
class ShowControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var showService: ShowService

    @Test
    fun `get show list`() {
        Mockito.`when`(showService.shows).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/shows")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }

    @Test
    fun `get show casts by id`() {
        Mockito.`when`(showService.getCastMembersById("Id")).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/shows/casts/Id")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }
}