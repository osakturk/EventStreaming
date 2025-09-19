package com.event.stream.controller

import com.event.stream.service.ShowService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
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
        Mockito.`when`(showService.getShows(0, 10)).thenReturn(Page.empty())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/shows")).andReturn()
        assertEquals(HttpStatus.OK.value(), result.response.status)
        assertEquals(0, result.response.contentLength)
    }

    @Test
    fun `get show count released after a specific year`() {
        Mockito.`when`(showService.countShowsReleasedInOrAfter(2020)).thenReturn(12)

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/shows/count/released-after")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
        assertEquals(12.toString(), result.response.contentAsString)
    }

    @Test
    fun `get show casts by id`() {
        Mockito.`when`(showService.getCastMembersById("Id")).thenReturn(arrayListOf())

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/shows/casts/Id")).andReturn()
        assertEquals(HttpStatus.OK.value(), result.response.status)
    }
}