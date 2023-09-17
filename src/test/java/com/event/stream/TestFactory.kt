package com.event.stream

import com.event.stream.constant.Constants.ORDER_SUCCESSFUL_MESSAGE
import com.event.stream.dto.Data
import com.event.stream.dto.StreamingResponseDTO
import com.event.stream.model.Event
import com.event.stream.model.Show
import com.event.stream.model.User
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

object TestFactory {
    fun getEvent() = Event("eventId",123L, "stream-started", "showId", "sytflix", "evendate")
    fun getEventList() = arrayListOf(getEvent())

    fun getShow() = Show("id","showId","cast", "country","dateAdded","description","director","duration","listedIn",
        "rating",2023,"title", "type","platform")
    fun getShowList() = arrayListOf(getShow())

    fun getUser() = User(123L,"birthDay", "mail", "firstname", "gender", "ipAddress", "country", "lastname")

    fun getUsersList() = arrayListOf(getUser())

    fun getStreamResponseDTO() = StreamingResponseDTO("stream-event-id", "event-description", Data(getShow(),"event-date", getUser()))

    fun getJsonData() = "id:664c318c-6f36-424f-adb9-56f7861aba27\n" +
            "event:show-liked\n" +
            "data:{\n" +
            "  \"show\":   {\n" +
            "    \"show_id\": \"s41\",\n" +
            "    \"cast\": \"Kuby Farner, Rakota Cotus, Uhivia Homabia, Elbert Jsai, Xixton Faoth, Qubecca Ketz\",\n" +
            "    \"country\": \"United States\",\n" +
            "    \"date_added\": \"July 16, 2021\",\n" +
            "    \"description\": \"Qot Cog! Bet waved-up fith Qeckey and ell of tis cals!\",\n" +
            "    \"director\": \"Henny Tcott\",\n" +
            "    \"duration\": \"3 Seasons\",\n" +
            "    \"listed_in\": \"Science Fiction\",\n" +
            "    \"rating\": \"TV-PG\",\n" +
            "    \"release_year\": 2021,\n" +
            "    \"title\": \"Kancy Kancy (Dhorts)\",\n" +
            "    \"type\": \"Movie\",\n" +
            "    \"platform\": \"Sysney\"\n" +
            "  },\n" +
            "  \"event_date\": \"27-02-2023 03:20:17.111\",\n" +
            "  \"user\": {\n" +
            "    \"id\": 116,\n" +
            "    \"date_of_birth\": \"31/10/1970\",\n" +
            "    \"email\": \"gbeeton37@wikimedia.org\",\n" +
            "    \"first_name\": \"Guenna\",\n" +
            "    \"gender\": \"Female\",\n" +
            "    \"ip_address\": \"96.34.189.33\",\n" +
            "    \"country\": \"TH\",\n" +
            "    \"last_name\": \"Beeton\"\n" +
            "  }\n" +
            "}"
}
