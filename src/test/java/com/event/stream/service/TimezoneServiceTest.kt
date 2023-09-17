package com.event.stream.service

import com.event.stream.TestFactory.getEventList
import com.event.stream.TestFactory.getTimezoneInfo
import com.event.stream.TestFactory.getTimezoneList
import com.event.stream.repository.TimezoneRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class TimezoneServiceTest {
    private var timezoneRepository: TimezoneRepository = mockk()
    private var timezoneService = TimezoneService(timezoneRepository)

    @Test
    fun `get time zone by country code`() {
        every {
            timezoneRepository.findTimezoneInfoByCountryCode(any())
        } returns getTimezoneList()

        val response = timezoneService.getTimezoneByCountryCode("CI")

        assertEquals(getTimezoneList().get(0).id, response.get(0).id)
        assertEquals(getTimezoneList().get(0).countryCode, response.get(0).countryCode)
        assertEquals(getTimezoneList().get(0).zoneName, response.get(0).zoneName)
    }
}

