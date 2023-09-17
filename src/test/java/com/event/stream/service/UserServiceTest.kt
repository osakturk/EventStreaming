package com.event.stream.service

import com.event.stream.TestFactory.getShow
import com.event.stream.TestFactory.getShowList
import com.event.stream.TestFactory.getUser
import com.event.stream.TestFactory.getUsersList
import com.event.stream.repository.ShowRepository
import com.event.stream.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class UserServiceTest {
    private var userRepository: UserRepository = mockk()
    private var userService = UserService(userRepository)

    @Test
    fun `get user list`() {
        every {
            userRepository.findAll()
        } returns getUsersList()

        val response = userService.users

        assertEquals(getUsersList(), response)
    }

    @Test
    fun `create user`() {
        every {
            userRepository.save(any())
        } returns getUser()

        val response = userService.addUser(getUser())

        assertEquals(getUser(), response)
    }
}

