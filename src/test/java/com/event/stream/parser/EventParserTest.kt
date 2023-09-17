import com.event.stream.TestFactory.getEvent
import com.event.stream.TestFactory.getShow
import com.event.stream.TestFactory.getUser
import com.event.stream.dto.EventParser
import com.event.stream.service.EventService
import com.event.stream.service.ShowService
import com.event.stream.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.nio.charset.StandardCharsets

class EventParserTest {
    private lateinit var eventParser: EventParser
    private val objectMapper = ObjectMapper()
    private val userService = mock(UserService::class.java)
    private val showService = mock(ShowService::class.java)
    private val eventService = mock(EventService::class.java)

    @BeforeEach
    fun setUp() {
        eventParser = EventParser(objectMapper, userService, showService, eventService)
    }

    @Test
    fun testParseJsonFromByteArray() {
        // Sample JSON data
        val jsonString = """
            id:664c318c-6f36-424f-adb9-56f7861aba27
            event:show-liked
            data:{
              "show":   {
                "show_id": "s41",
                "cast": "Kuby Farner, Rakota Cotus, Uhivia Homabia, Elbert Jsai, Xixton Faoth, Qubecca Ketz",
                "country": "United States",
                "date_added": "July 16, 2021",
                "description": "Qot Cog! Bet waved-up fith Qeckey and ell of tis cals!",
                "director": "Henny Tcott",
                "duration": "3 Seasons",
                "listed_in": "Science Fiction",
                "rating": "TV-PG",
                "release_year": 2021,
                "title": "Kancy Kancy (Dhorts)",
                "type": "Movie",
                "platform": "Sysney"
              },
              "event_date": "27-02-2023 03:20:17.111",
              "user": {
                "id": 116,
                "date_of_birth": "31/10/1970",
                "email": "gbeeton37@wikimedia.org",
                "first_name": "Guenna",
                "gender": "Female",
                "ip_address": "96.34.189.33",
                "country": "TH",
                "last_name": "Beeton"
              }
            }
        """.trimIndent()

        // Convert JSON string to byte array
        val byteArray = jsonString.toByteArray(StandardCharsets.UTF_8)

        // Mock the behavior of userService, showService, and eventService
        val sampleUser = getUser()
        val sampleShow = getShow()
        val sampleEvent = getEvent()

        `when`(userService.addUser(sampleUser)).thenReturn(sampleUser)
        `when`(showService.addShow(sampleShow)).thenReturn(sampleShow)
        `when`(eventService.addEvent(any())).thenReturn(sampleEvent)

        // Call the method to be tested
        val result = eventParser.parseJsonFromByteArray(byteArray)

        // Assertions
        assertEquals("664c318c-6f36-424f-adb9-56f7861aba27", result.id)
        assertEquals("show-liked", result.event)

        // Verify that the userService, showService, and eventService methods were called
        verify(userService, times(1)).addUser(any())
        verify(showService, times(1)).addShow(any())
        verify(eventService, times(1)).addEvent(any())
    }
}
