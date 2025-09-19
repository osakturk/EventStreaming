package com.event.stream.service

import com.event.stream.TestFactory.getUser
import com.event.stream.TestFactory.getUsersList
import com.event.stream.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class UserServiceTest {
    private var userRepository: UserRepository = mockk()
    private var userService = UserService(userRepository)

    @Test
    fun `get user list`() {
        val userList = getUsersList()
        val userPage = PageImpl(userList)

        every {
            userRepository.findAll(PageRequest.of(0, 10))
        } returns userPage

        val response = userService.getUsers(0,10)

        assertEquals(userPage, response)
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

