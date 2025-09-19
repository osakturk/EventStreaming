package com.event.stream.service


import com.event.stream.TestFactory.getStreamResponseDTO
import com.event.stream.dto.EventParser
import com.event.stream.dto.StreamingResponseDTO
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

class StreamServiceTest {

    private var webClient: WebClient = mockk()
    private var eventParser: EventParser = mockk()
    var streamService = StreamService(webClient, eventParser)

    @Test
    fun `allPlatformStreams emits three items`() {
        val mockDto = getStreamResponseDTO()
        every { eventParser.parseJsonFromByteArray(any()) } returns mockDto

        every { webClient.get() } returns mockk(relaxed = true)
        val uriSpec = webClient.get()
        val headersSpec = mockk<WebClient.RequestHeadersSpec<*>>(relaxed = true)
        val responseSpec = mockk<WebClient.ResponseSpec>(relaxed = true)

        every { uriSpec.uri(any<String>()) } returns headersSpec
        every { headersSpec.retrieve() } returns responseSpec
        every { responseSpec.bodyToFlux(ByteArray::class.java) } returns Flux.just(mockDto.toString().toByteArray())
        every { eventParser.parseJsonFromByteArray(any()) } returns mockDto

        val result = streamService.allPlatformStreams().collectList().block()

        assert(result != null)
        assert(result!!.all { it == mockDto })
    }
}