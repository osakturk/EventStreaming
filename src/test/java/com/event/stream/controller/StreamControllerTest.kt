package com.event.stream.controller

import com.event.stream.TestFactory.getStreamResponseDTO
import com.event.stream.service.StreamService
import com.event.stream.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import reactor.core.publisher.Flux

@WebMvcTest(controllers = [StreamController::class])
class StreamControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var streamService: StreamService

    @Test
    fun `get sytazon events`() {
        Mockito.`when`(streamService.sytazonData()).thenReturn(Flux.just(getStreamResponseDTO()))

        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/stream/collect")).andReturn()
        assertEquals(result.response.status, HttpStatus.OK.value())
    }
}